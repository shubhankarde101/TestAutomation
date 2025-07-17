const {test, expect} = require('@playwright/test');

test('MyTest1__MapSvg', async ({browser}) =>
{
  const context = await browser.newContext();
  const page =  await context.newPage();
  await page.goto("https://petdiseasealerts.org/forecast-map");
  const states = ['California']
  for (const state of states) {
    const framePage = page.frameLocator("xpath=.//iframe[contains(@id, 'map-instance')]");
    await framePage.locator("xpath=//*[name()='svg']//*[local-name()='g' and @id='"+state.toLowerCase()+"']").click()
    await page.waitForLoadState('networkidle');
    await expect(framePage.locator("xpath=//span[text()='"+state+"']")).toBeVisible();
    await page.reload();
  }
}

)



