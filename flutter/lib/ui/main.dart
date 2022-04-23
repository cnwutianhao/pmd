import 'package:flutter/material.dart';
import 'package:play_mobile_development/model/news_list.dart';
import 'package:play_mobile_development/network/dio_client.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Play Mobile Development',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      debugShowCheckedModeBanner: false,
      home: const MyHomePage(title: 'News List'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final DioClient _client = DioClient();

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: FutureBuilder<NewsList?>(
          future: _client.getNewsList(timestamp: ''),
          builder: (BuildContext context, AsyncSnapshot<NewsList?> snapshot) {
            NewsList? newsCollection = snapshot.requireData;
            int count = 0;
            if (newsCollection != null && newsCollection.newsList.isNotEmpty) {
              count = newsCollection.newsList.length;
            }
            return ListView.separated(
              itemBuilder: (context, index) =>
                  newsListItem(index, newsCollection),
              separatorBuilder: (context, index) => const Divider(
                color: Colors.black,
              ),
              itemCount: count,
            );
          },
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  ListTile newsListItem(int index, NewsList? newsCollection) {
    String title = "";
    String description = "";
    String image = "";
    if (newsCollection != null) {
      title = newsCollection.newsList[index].title;
      description = newsCollection.newsList[index].description;
      image = newsCollection.newsList[index].image;
    }
    return ListTile(
      leading: Image.network(image),
      title: Text(title, maxLines: 1),
      subtitle: Text(description, maxLines: 2),
    );
  }
}
