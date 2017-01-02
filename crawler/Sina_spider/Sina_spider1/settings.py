# encoding=utf-8
BOT_NAME = 'Sina_spider1'

SPIDER_MODULES = ['Sina_spider1.spiders']
NEWSPIDER_MODULE = 'Sina_spider1.spiders'

DOWNLOADER_MIDDLEWARES = {
    "Sina_spider1.middleware.UserAgentMiddleware": 401,
    "Sina_spider1.middleware.CookiesMiddleware": 402,
}

ITEM_PIPELINES = {
    'Sina_spider1.pipelines.Neo4jDBPipleline': 300,
}

DOWNLOAD_DELAY = 10  # 间隔时间
# CONCURRENT_ITEMS = 1000
# CONCURRENT_REQUESTS = 100
# REDIRECT_ENABLED = False
# CONCURRENT_REQUESTS_PER_DOMAIN = 100
# CONCURRENT_REQUESTS_PER_IP = 0
# CONCURRENT_REQUESTS_PER_SPIDER=100
# DNSCACHE_ENABLED = True
LOG_LEVEL = 'WARNING'    # 日志级别
# CONCURRENT_REQUESTS = 70

"""
输入你的微博账号和密码，可去淘宝买，一元七个。
建议买几十个，微博限制的严，太频繁了会出现302转移。
或者你也可以把时间间隔调大点。
"""
WEIBO_ACCOUNT = [
    {'no': '微博账号', 'psw': '密码'},
]

# Neo4j 数据库配置
DB_INFO = {
    "host": "localhost",
    "http_port": 7474,
    "user": "neo4j",
    "password": "neo4j"
}

# 仅爬取数据库中信息不完整的账号
CRAWL_DB_EMPTY_ACCOUNT_ONLY = True