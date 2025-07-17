import { defineConfig } from "cypress";



export default defineConfig({
  env: {
    baseUrl: 'https://admin-qa.teesnap.com/teesnap-portal/login',
  },
  e2e: {
    reporter: "cypress-multi-reporters",
    reporterOptions: {
      reporterEnabled: "mochawesome",
      mochawesomeReporterOptions: {
      reportDir: 'cypress/reports/mocha',
      quite: true,
      html: false,
      json: true
      }
    },
    screenshotsFolder: "cypress/reports/mochareports/assets",
    screenshotOnRunFailure: true,
    video: true,
    specPattern: 'cypress/integration/**/*.js',
    experimentalSessionAndOrigin: true,
  },
  defaultCommandTimeout: 15000,
});
