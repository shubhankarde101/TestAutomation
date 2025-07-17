const {test, expect} = require('@playwright/test');


test('MyTest1__browser', async ({browser}) =>
{
  const context = await browser.newContext();
  const page =  await context.newPage();
  await page.goto("https://rahulshettyacademy.com/loginpagePractise/")
  
})

test('MyTest1__page', async ({page}) =>
{
  
  await page.goto("https://rahulshettyacademy.com/loginpagePractise/")
  console.log(await page.title)
  await expect(page).toHaveTitle("abcd")
})

test.only('MyTest1__incorrect login', async ({page}) =>
{
  
  await page.goto("https://rahulshettyacademy.com/loginpagePractise/")
  console.log(await page.title)
  await expect(page).toHaveTitle("LoginPage Practise | Rahul Shetty Academy")
  await page.locator("#username").type("Rahul")
  await page.locator("[type='password']").type("Rahul")
  await page.locator("#signInBtn").click()
  


})

