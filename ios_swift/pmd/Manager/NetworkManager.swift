//
//  NetworkManager.swift
//  pmd
//
//  Created by 吴天昊 on 2022/4/17.
//

import Foundation

class NetworkManager {
    var newsList: [News] = []
    private let baseUrl = "https://api.ithome.com/json/newslist/news?r=1647302843000"

    func fetchNewsList(completionHandler: @escaping ([News]) -> Void) {
        let url = URL(string: baseUrl)!
        let task = URLSession.shared.dataTask(with: url, completionHandler: { data, response, error in
            if let error = error {
                print("获取NewsList时出错: \(error)")
                return
            }

            guard let httpResponse = response as? HTTPURLResponse,
                  (200 ... 299).contains(httpResponse.statusCode)
            else {
                print("Response出错，意外的状态码: \(response)")
                return
            }

            if let data = data,
               let newsList = try? JSONDecoder().decode(NewsList.self, from: data)
            {
                completionHandler(newsList.newslist ?? [])
            }
        })
        task.resume()
    }
}
