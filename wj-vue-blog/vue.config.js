//跨域访问
module.exports = {
    outputDir: 'dist',   //build输出目录
    assetsDir: 'assets', //静态资源目录（js, css, img）
    lintOnSave: false, //是否开启eslint
    devServer: {
        open: true, //是否自动弹出电脑默认浏览器页面
        host: "localhost", 
        port: '9999', 
        https: false,   //是否使用https协议
        hotOnly: false, //是否开启热更新
        proxy: {
            '/api': {
                target: 'http://localhost:8888',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            },
            '/appy': {
                target: 'http://cdn.sojson.com/_city.json',
                changeOrigin: true,
                pathRewrite: {
                    '^/appy': ''
                }
            }
        }
    }
}