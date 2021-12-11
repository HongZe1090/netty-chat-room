/* eslint-disable no-mixed-spaces-and-tabs */
const webpack = require('webpack')
const port = 9000;
const host = 'localhost'
const remoteIp = 'http://localhost:9000' // 前端静态资源代理地址

module.exports = {
    // 打包输入文件夹路径 未添加
    // vue项目的路径
    devServer: {
        port: 9000,     // 端口号
    },
    lintOnSave: false,
    configureWebpack: (config) => {
        // 全局引入jquery
        config.plugins.push(
        	new webpack.ProvidePlugin({
        		$: 'jquery',
        	})
        )
	},
}
