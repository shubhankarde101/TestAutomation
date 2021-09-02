from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager

from context.config import settings
import logging as log


class Driver(object):
    """Singleton class for interacting with the selenium webdriver object"""
    instance = None

    class SeleniumDriverNotFound(Exception):
        pass

    @classmethod
    def get_instance(cls):
        if cls.instance == None:
            cls.instance = Driver()
        return cls.instance

    def __init__(self):
        logger = log.getLogger(__name__)
        log.basicConfig(filename="test.log")
        if settings.browser == "chrome":
            self.driver = webdriver.Chrome(ChromeDriverManager().install())
            log.info("Driver instanciated")
        elif settings.browser == "firefox":
            self.driver = webdriver.Firefox()
        else:
            print("Driver not found")

    def get_driver(self):
        return self.driver

    def stop_instance(self):
        self.driver.quit()
        instance = None
        log.info("Driver closed")

    def clear_cookies(self):
        self.driver.delete_all_cookies()

    def navigate(self, url):
        self.driver.get(url)


driver = Driver.get_instance()
