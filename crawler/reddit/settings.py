# Scrapy settings for shopbot project
#
# For simplicity, this file contains only the most important settings by
# default. All the other settings are documented here:
#
# http://doc.scrapy.org/topics/settings.html
#

BOT_NAME = 'mandelbrot'

FEED_FORMAT = 'jsonlines'
FEED_URI = 'file:data/%(name)s.json'

DOWNLOAD_TIMEOUT = 60
DOWNLOADER_DEBUG = True

SUCCESS_LOG_RATIO = .01
EMPTY_LOG_RATIO = .1
CRASH_LOG_RATIO = 1

# We should never go this deep
DEPTH_LIMIT = 100

CONCURRENT_REQUESTS_PER_DOMAIN = 2

#DOWNLOADER_DELAY = 1

LOG_STDOUT = True
LOG_UNSERIALIZABLE_REQUESTS = True

ROBOTSTXT_OBEY = True

SPIDER_MODULES = ['shopbot.spiders']
NEWSPIDER_MODULE = 'shopbot.spiders'

DOWNLOAD_HANDLERS = {
    'http': 'scrapy_webdriver.download.WebdriverDownloadHandler',
    'https': 'scrapy_webdriver.download.WebdriverDownloadHandler',
}

#WEBDRIVER_BROWSER = 'PhantomJS'
WEBDRIVER_BROWSER = 'Firefox'
WEBDRIVER_OPTIONS = {
      'service_args': ['--load-images=false'],
}
WEBDRIVER_HANG_TIMEOUT = 25

USER_AGENT = "ModernMan"
