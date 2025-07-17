const { defineConfig } = require("cypress");
module.exports = defineConfig({
  env: {
    baseUrl: 'https://practicesoftwaretesting.com/#/',   
  },
  e2e: {
    specPattern: 'cypress/integration/examples/*.js',
    experimentalSessionAndOrigin: true

},
defaultCommandTimeout: 15000
});
