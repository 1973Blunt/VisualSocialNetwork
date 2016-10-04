# encoding=utf-8
from py2neo import Graph, Node, Relationship
from items import InformationItem, TweetsItem, FollowsItem, FansItem
import settings

class Neo4jDBPipleline(object):
    def __init__(self):
        # self.db = Graph(host="localhost", user="neo4j", password="neo4j")
        db_info = settings.DB_INFO
        self.db = Graph(host=db_info["host"], http_port=db_info["http_port"],
                        user=db_info["user"], password=db_info["password"])

    def process_item(self, item, spider):
        """ 判断item的类型，并作相应的处理，再入数据库 """
        if isinstance(item, InformationItem):
            usr = Node("WeiboUser", **dict(item))
            self.db.merge(usr, "WeiboUser", "wb_usr_id")
        elif isinstance(item, TweetsItem):
            weibo = Node("WeiboTweets", **dict(item))
            self.db.merge(weibo, "WeiboTweets", "wb_tt_id")
            usr = Node("WeiboUser", wb_usr_id=weibo["wb_usr_id"])
            fan_follow_sb = Relationship(usr, "TWEETS", weibo)
            self.db.merge(fan_follow_sb)
        elif isinstance(item, FollowsItem):
            followsItems = dict(item)
            follows = followsItems.pop("follows")
            fan = Node("WeiboUser", wb_usr_id=followsItems["wb_usr_id"])
            for sb_id in follows:
                sb = Node("WeiboUser", wb_usr_id=sb_id)
                fan_follow_sb = Relationship(fan, "FOLLOWS", sb)
                self.db.merge(fan_follow_sb)
        elif isinstance(item, FansItem):
            fansItems = dict(item)
            fans = fansItems.pop("fans")
            sb = Node("WeiboUser", wb_usr_id=fansItems["wb_usr_id"])
            for fan_id in fans:
                fan = Node("WeiboUser", wb_usr_id=fan_id)
                fan_follow_sb = Relationship(fan, "FOLLOWS", sb)
                self.db.merge(fan_follow_sb)
        return item
