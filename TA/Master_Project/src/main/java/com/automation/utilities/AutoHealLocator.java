package com.automation.utilities;

import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AutoHealLocator {

	static DocumentBuilderFactory docFactory;
	static DocumentBuilder docBuilder;
	static Document doc;
	static Document xmldoc;
	static XPath xpath;
	static Element page = null;
	static Map<String, String> li = new HashMap<String, String>();
	static String fileName = System.getProperty("user.dir") + File.separator + "historical_master_locator_data.xml";
	static int MAX_PRIORITY_ITR;
	static String trigger;

//###########################################################################---Initialize----#################################################################	

	public static void initializeParser(String url, String trigger_param) throws ParserConfigurationException {

		docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.newDocument();
		xpath = XPathFactory.newInstance().newXPath();
		MAX_PRIORITY_ITR = 3;
		trigger = trigger_param;
		create_root_node(url);

	}

	private static void create_root_node(String url) {
		// TODO Auto-generated method stub
		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);
		page = create_Element(rootElement, "page");
		add_Element_Attribute(page, "baseUrl", url);

	}

	private static NodeList getNodesByXpath(String expr) throws XPathExpressionException {
		NodeList nodelist = (NodeList) xpath.compile(expr).evaluate(xmldoc, XPathConstants.NODESET);
		return nodelist;

	}
//###########################################################################---Main Logic----#################################################################	

	public static String HealLocator(WebDriver driver, String strategy, String locator) {
		// Historical data analysis
		String return_strategy_locator = locator;
		List<WebElement> element_to_be_returned = null;
		File f_dest = new File(fileName);
		// System.out.println("File Exists: " + f_dest.exists());
		Wait<WebDriver> wait = new WebDriverWait(driver, 10);
		By by = null;
		by = getWebElementFromDiffLocator(strategy, locator);
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		} catch (Exception timeout) {
			System.out.println("Element not present in the Dom for this locator: \"" + locator + "\"----------");
		}
		element_to_be_returned = driver.findElements(by);
		if (trigger.equalsIgnoreCase("ON")) {
			if (f_dest.exists()) {
				try {

					xmldoc = docBuilder.parse(fileName);
					xmldoc.getDocumentElement().normalize();
					// System.out.println("Returned list size of existing locator: " + locator + "
					// in alternate run is: " + element_to_be_returned.size());
					if (element_to_be_returned.size() == 0) {
						NodeList nodeList = getNodesByXpath(".//element[@locator=\"" + locator + "\"]");
						if (nodeList.getLength() > 0) {
							System.out.println("Locator already added in master xml: " + locator);
							for (int i = 0; i < nodeList.getLength(); i++) {
								System.out.println("Existing locator not working due to new build deployment");
								int priority_iteration = MAX_PRIORITY_ITR;
								for (int p = 1; p < priority_iteration; p++) {
									if (return_strategy_locator.equalsIgnoreCase(locator)) {
										return_strategy_locator = get_alternate_locator_from_master_xml(driver, locator,
												Integer.toString(p));
									}
								}

								if (return_strategy_locator.equalsIgnoreCase(locator)) {
									System.out.println("No alternate strategy found for this locator: " + locator);
								} else {
									System.out.println("Alternate strategy: \"" + return_strategy_locator
											+ "\" found for this locator: " + locator);

								}

							}

						} else {
							System.out.println("Locator not added in master xml: " + locator);
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

				try {

					Element node = null;
					node = add_Node(page, strategy, locator, "Existing");
					int count = -1;
					WebElement element = null;
					element = driver.findElement(by);
					count = element_to_be_returned.size();
					Map<String, String> map = getAllAttributes(driver, element);
					String tagElmnt = element.getTagName();
					String txt = element.getText();
					String absoluteXpath = getAbsoluteXpath(driver, element);
					generate_Alternate_Strategy(map, tagElmnt, txt, node, driver, count, element, absoluteXpath);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return return_strategy_locator;

	}

	private static void generate_Alternate_Strategy(Map<String, String> map, String tag, String text, Element node,
			WebDriver driver, int elmnt_count, WebElement ele, String absolute_Xpath_existing) {
		// TODO Auto-generated method stub
		// System.out.println("Tag is: " + tag);
		if (map.size() > 0) {
			Iterator<Entry<String, String>> hmIterator = map.entrySet().iterator();
			while (hmIterator.hasNext()) {
				Entry<String, String> mapElemnt = hmIterator.next();
				String key = mapElemnt.getKey();
				String value = mapElemnt.getValue();
				parse_alt_strategy(key, value, tag, text, driver, elmnt_count, map, absolute_Xpath_existing);
			}
		}
		if (!text.trim().isEmpty()) {
			String str_loc_without_tag = ".//*[contains(.,\"" + text.trim() + "\")]";
			String str_loc_with_tag = ".//" + tag + "[contains(.,\"" + text.trim() + "\")]";
			validate_alt_strategy(str_loc_without_tag, driver, elmnt_count, "1", map, tag, text,
					absolute_Xpath_existing);
			validate_alt_strategy(str_loc_with_tag, driver, elmnt_count, "2", map, tag, text, absolute_Xpath_existing);

		}

		validate_alt_strategy(absolute_Xpath_existing, driver, elmnt_count, "3", map, tag, text,
				absolute_Xpath_existing);

		WebElement parent = getParentElement(driver, ele);
		String childposition = getRelativePosition(driver, ele).toString();
		// System.out.println("Relative position for this element is: " +
		// childposition);
		Map<String, String> mapParent = getAllAttributes(driver, parent);
		String tag_Parent = parent.getTagName();
		if (mapParent.size() > 0) {
			Iterator<Entry<String, String>> hmIterator1 = mapParent.entrySet().iterator();
			while (hmIterator1.hasNext()) {
				Entry<String, String> mapElemnt = hmIterator1.next();
				String key = mapElemnt.getKey();
				String value = mapElemnt.getValue();
				parse_alt_strategy_for_parent(key, value, tag_Parent, tag, childposition, driver, elmnt_count,
						absolute_Xpath_existing, map, text);
			}
		}

		for (String i : li.keySet()) {

			add_Node_Elements(node, "xpath", i, li.get(i));
		}

		li.clear();

	}

	private static void parse_alt_strategy_for_parent(String key, String value, String tag_Parent, String tag,
			String childposition, WebDriver driver, int elmnt_count, String absolute_Xpath_existing,
			Map<String, String> map, String text) {
		// TODO Auto-generated method stub
		String str_loc_with_parent_tag = ".//" + tag_Parent + "[@" + key + "=\"" + value + "\"]//" + tag + "["
				+ childposition + "]";
		String str_loc_without_parent_tag = ".//*" + "[@" + key + "=\"" + value + "\"]//" + tag + "[" + childposition
				+ "]";
		validate_alt_strategy(str_loc_with_parent_tag, driver, elmnt_count, "3", map, tag, text,
				absolute_Xpath_existing);
		validate_alt_strategy(str_loc_without_parent_tag, driver, elmnt_count, "3", map, tag, text,
				absolute_Xpath_existing);

	}

	private static void parse_alt_strategy(String key, String value, String tag, String text, WebDriver driver,
			int elmnt_count, Map<String, String> map, String absolute_Xpath_existing) {
		// TODO Auto-generated method stub
		String str_loc_with__tag = ".//" + tag + "[@" + key + "=\"" + value + "\"]";
		String str_loc_without_tag = ".//*" + "[@" + key + "=\"" + value + "\"]";
		validate_alt_strategy(str_loc_without_tag, driver, elmnt_count, "2", map, tag, text, absolute_Xpath_existing);
		validate_alt_strategy(str_loc_with__tag, driver, elmnt_count, "1", map, tag, text, absolute_Xpath_existing);

	}

	private static void validate_alt_strategy(String str_loc, WebDriver driver, int elmnt_count, String priority,
			Map<String, String> map_existing, String tag_existing, String text_existing,
			String absolute_Xpath_existing) {
		// TODO Auto-generated method stub
		try {
			int count = driver.findElements(By.xpath(str_loc)).size();
			if (count == elmnt_count) {

				WebElement ele = driver.findElement(By.xpath(str_loc));
				Map<String, String> map_alt = getAllAttributes(driver, ele);
				String tagEle = ele.getTagName();
				String txt = ele.getText();
				String absolutexpath = getAbsoluteXpath(driver, ele);
				if (absolutexpath.equalsIgnoreCase(absolute_Xpath_existing)) {
					if (tagEle.equalsIgnoreCase(tag_existing) && map_alt.equals(map_existing)
							&& txt.equalsIgnoreCase(text_existing)) {
						li.putIfAbsent(str_loc, priority);

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String get_alternate_locator_from_master_xml(WebDriver driver, String locator, String priority) {
		// TODO Auto-generated method stub.
		String strategy_locator = locator;
		try {
			NodeList nodeLists_alts = getNodesByXpath(
					"//element[@locator=\"" + locator + "\"]//alt[@priority=\"" + priority + "\"]");
			for (int j = 0; j < nodeLists_alts.getLength(); j++) {
				Node node_alt = (Node) nodeLists_alts.item(j);
				Element alt_ele = (Element) node_alt;
				if (driver.findElements(By.xpath(alt_ele.getAttribute("locator"))).size() > 0) {
					strategy_locator = alt_ele.getAttribute("locator");
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strategy_locator;
	}

	private static By getWebElementFromDiffLocator(String strategy, String locator) {
		// TODO Auto-generated method stub
		By by = null;
		try {

			if (strategy.trim().equalsIgnoreCase("xpath")) {
				by = By.xpath(locator);
			} else if (strategy.trim().equalsIgnoreCase("css")) {
				by = By.cssSelector(locator);
			} else if (strategy.trim().equalsIgnoreCase("id")) {
				by = By.id(locator);

			} else if (strategy.trim().equalsIgnoreCase("name")) {
				by = By.name(locator);

			} else if (strategy.trim().equalsIgnoreCase("class")) {
				by = By.className(locator);

			} else if (strategy.trim().equalsIgnoreCase("tag")) {
				by = By.tagName(locator);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return by;

	}

//###########################################################################---Creating XML Repository----#################################################################	

	public static void write_Content_To_Xml() {
		try {

			// write content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// enable indent on xml file
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			File f_dest = new File(fileName);
			if (trigger.equalsIgnoreCase("OFF")) {
				if (f_dest.exists()) {
					FileUtils.deleteQuietly(f_dest);

				}
			} else if (trigger.equalsIgnoreCase("ON") && !f_dest.exists()) {
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(f_dest);
				transformer.transform(source, result);
				System.out.println("----File Saved----");
			}

		} catch (TransformerException tfe) {
			tfe.printStackTrace();

		}

	}

	private static Element create_Element(Element eleRoot, String name) {
		// TODO Auto-generated method stub
		Element ele = doc.createElement(name);
		eleRoot.appendChild(ele);
		return ele;

	}

	private static void add_Element_TxtNode(Element ele, String nodeName) {
		// TODO Auto-generated method stub
		ele.appendChild(doc.createTextNode(nodeName));

	}

	private static Element add_Node(Element page, String strategy, String value, String priority) {
		// TODO Auto-generated method stub
		Element existingRootElement = create_Element(page, "element");
		add_Element_Attribute(existingRootElement, "strategy", strategy);
		add_Element_Attribute(existingRootElement, "locator", value);
		add_Element_Attribute(existingRootElement, "priority", priority);
		return existingRootElement;

	}

	private static void add_Node_Elements(Element node, String strategy, String value, String priority) {
		// TODO Auto-generated method stub
		Element alt_1 = create_Element(node, "alt");
		add_Element_Attribute(alt_1, "strategy", strategy);
		add_Element_Attribute(alt_1, "locator", value);
		add_Element_Attribute(alt_1, "priority", priority);
		add_Element_TxtNode(alt_1, Timestamp.from(Instant.now()).toString());

	}

	private static void add_Element_Attribute(Element ele, String attrName, String attrVal) {
		// TODO Auto-generated method stub
		Attr attr = doc.createAttribute(attrName);
		attr.setValue(attrVal);
		ele.setAttributeNode(attr);

	}

//##########################################################---Common Javascript Functions---##################################################################################	

	private static String getAbsoluteXpath(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript(
				"function getAbsoluteXpath(element)\r\n" + "{\r\n" + "\r\n" + "  var xpath = \"\";\r\n" + "  do{\r\n"
						+ "      xpath = getElementLevelIdentifier(element,xpath);\r\n"
						+ "      element = element.parentElement;\r\n" + "\r\n" + "\r\n"
						+ "   }while(element.parentElement!=null)\r\n" + "\r\n"
						+ "  return getElementLevelIdentifier(element,xpath)\r\n" + "\r\n" + "}\r\n" + "\r\n" + "\r\n"
						+ "function getElementLevelIdentifier(element,xpath)\r\n" + "{\r\n" + "\r\n"
						+ "   var position=1;\r\n" + "   var tagName = element.localName;\r\n"
						+ "   while(element.previousSibling != null)\r\n" + "     {\r\n" + "\r\n"
						+ "       if(element.previousSibling.localName == tagName)\r\n" + "         {\r\n" + "\r\n"
						+ "             position++;              \r\n" + "\r\n" + "	  }\r\n" + "\r\n"
						+ "       element = element.previousSibling;\r\n" + "\r\n" + "\r\n" + "      }\r\n"
						+ "  xpath = \"//\" + tagName + \"[\" + position + \"]\"+ xpath;\r\n" + "   \r\n"
						+ "  return xpath;\r\n" + "\r\n" + "}\r\n" + "\r\n" + "return getAbsoluteXpath(arguments[0]);",
				element);

	}

	private static WebElement getParentElement(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (WebElement) js.executeScript("return arguments[0].parentNode;\r\n" + "", element);

	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getAllAttributes(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (Map<String, String>) js.executeScript("var items = {};\r\n" + "\r\n"
				+ "for(index=0;index<arguments[0].attributes.length;++index)\r\n" + "{\r\n"
				+ "  items[arguments[0].attributes[index].name]= arguments[0].attributes[index].value\r\n" + "};\r\n"
				+ "\r\n" + "return items;", element);

	}

	private static Object getRelativePosition(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("function getElementPosition(element)\r\n" + "{\r\n" + "\r\n" + "   var position=1;\r\n"
				+ "   var tagName = element.localName;\r\n" + "   while(element.previousSibling != null)\r\n"
				+ "     {\r\n" + "\r\n" + "       if(element.previousSibling.localName == tagName)\r\n"
				+ "         {\r\n" + "\r\n" + "             position++;              \r\n" + "\r\n" + "	  }\r\n"
				+ "\r\n" + "       element = element.previousSibling;\r\n" + "\r\n" + "\r\n" + "      }\r\n" + "   \r\n"
				+ "  return position;\r\n" + "}\r\n" + "\r\n" + "return getElementPosition(arguments[0]);", element);

	}

}
