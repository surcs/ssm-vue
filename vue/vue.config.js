const webpack = require('webpack')

module.exports = {
    devServer: {
      open: true,
      port: 8848
    },
    configureWebpack: {    
      plugins: [      
          new webpack.ProvidePlugin({        
              $: 'jquery',        
              jQuery: 'jquery',        
              'windows.jQuery': 'jquery'      
          })    
      ]  
  }
}
  
  