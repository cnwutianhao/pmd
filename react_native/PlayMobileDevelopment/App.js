// 参考：https://blog.csdn.net/kimi985566/article/details/85088889
// https://www.reactnativeschool.com/migrating-from-component-state-to-hooks-for-a-fetch-request

import React, { Component } from 'react';
import { ActivityIndicator, FlatList, Image, StyleSheet, Text, View, SafeAreaView } from 'react-native';

const REQUEST_URL = "https://api.ithome.com/json/newslist/news?r=1647302843000";

export default class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      data: [],
      loaded: false
    };
    // 在ES6中，如果在自定义的函数里使用了this关键字，则需要对其进行“绑定”操作，否则this的指向会变为空
    // 像下面这行代码一样，在constructor中使用bind是其中一种做法（还有一些其他做法，如使用箭头函数等）
    this.fetchData = this.fetchData.bind(this);
  }

  componentDidMount() {
    this.fetchData();
  }

  fetchData() {
    fetch(REQUEST_URL)
      .then(response => response.json())
      .then(responseData => {
        this.setState({
          data: this.state.data.concat(responseData.newslist),
          loaded: true
        })
      });
  }

  static renderLoadingView() {
    return (
      <View style={styles.container}>
        <ActivityIndicator size="large" color="#8bc9ff" />
      </View>
    );
  }

  static renderNews({ item }) {
    // { item }是一种“解构”写法，请阅读ES2015语法的相关文档
    // item也是FlatList中固定的参数名，请阅读FlatList的相关文档
    return (
      <View style={styles.container}>
        <Image
          source={{ uri: item.image }}
          style={styles.thumbnail} />
        <View style={styles.rightContainer}>
          <Text numberOfLines={1} style={styles.title}>{item.title}</Text>
          <Text numberOfLines={2} style={styles.description}>{item.description}</Text>
        </View>
      </View>
    );
  }

  render() {
    if (!this.state.loaded) {
      return App.renderLoadingView();
    }

    return (
      <SafeAreaView style={{ flex: 1 }}>
        <FlatList
          data={this.state.data}
          ItemSeparatorComponent={ItemDivideComponent}
          renderItem={App.renderNews}
          style={styles.list}
          keyExtractor={(item, index) => item.newsid} />
      </SafeAreaView>
    );
  }
}

class ItemDivideComponent extends Component {
  render() {
    return (
      <View style={{ height: 0.5, backgroundColor: 'gray' }} />
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "center",
    backgroundColor: "#F5FCFF",
    padding: 5
  },
  rightContainer: {
    flex: 1,
    marginLeft: 10,
    flexDirection: 'column',
  },
  title: {
    color: '#000',
    fontWeight: 'bold',
    fontSize: 20,
    marginBottom: 9,
    justifyContent: 'flex-start'
  },
  description: {
    fontSize: 15,
    marginBottom: 5,
  },
  thumbnail: {
    width: 120,
    height: 90
  },
  list: {
    backgroundColor: "#FFF"
  }
});