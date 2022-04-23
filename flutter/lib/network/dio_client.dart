import 'package:dio/dio.dart';
import 'package:play_mobile_development/model/news_list.dart';

class DioClient {
  final Dio _dio = Dio();

  final _baseUrl = "https://api.ithome.com/json/newslist/news?r=1648731540000";

  Future<NewsList> getNewsList({required String timestamp}) async {
    Response newsListData = await _dio.get(_baseUrl);
    NewsList newsList = NewsList.fromJson(newsListData.data);
    return newsList;
  }
}
