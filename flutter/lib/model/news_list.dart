import 'dart:convert';

class News {
  final String title;
  final String description;
  final String image;

  News({
    required this.title,
    required this.description,
    required this.image,
  });

  factory News.fromJson(Map<String, dynamic> json) => News(
        title: json["title"],
        description: json["description"],
        image: json["image"],
      );

  Map<String, dynamic> toJson() => {
        "title": title,
        "description": description,
        "image": image,
      };
}

class NewsList {
  final List<News> newsList;

  NewsList({required this.newsList});

  factory NewsList.fromRawJson(String str) =>
      NewsList.fromJson(json.decode(str));

  factory NewsList.fromJson(Map<String, dynamic> json) => NewsList(
      newsList: List<News>.from(json["newslist"].map((x) => News.fromJson(x))));

  Map<String, dynamic> toJson() =>
      {"newslist": List<dynamic>.from(newsList.map((x) => x.toJson()))};
}
