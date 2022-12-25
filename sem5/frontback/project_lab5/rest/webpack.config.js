// webpack.config.js
const path = require("path");
const TerserPlugin = require("terser-webpack-plugin");

module.exports = {
  mode: 'production',
  entry: './script/start.js',
  module: {
    rules: [
      { test: /\.css$/, use: ["style-loader", "css-loader"] },
      { test: /\.(js)$/, use: "babel-loader", exclude: "/node_modules/" }
    ],
  },
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: 'bundle.js',
    publicPath: "/dist",
    clean: true
  },
  devServer: {
    port: 9090,
    static: {
      directory: path.join(__dirname)
    },
    client: {
      overlay: true
    }
  },

  optimization: {
    minimize: true,
    minimizer: [new TerserPlugin({
      terserOptions: {
        format: {
          comments: false,
        }
      },
      extractComments: false,
      parallel: true
    })]
  }
};
