//
// Created by 吴天昊 on 2022/4/9.
//

import Foundation

class NetworkManager: ObservableObject {
    @Published var newsList = NewsList(newslist: [])
    @Published var loading = false
    private let base_url = "https://api.ithome.com/json/newslist/news?r=1647302843000"

    init() {
        loading = true
        networking()
    }

    private func networking() {
        guard let url = URL(string: base_url) else { return }
        URLSession.shared.dataTask(with: url) { data, _, error in
            do {
                guard let data = data else { return }
                let tempNewList = try! JSONDecoder().decode(NewsList.self, from: data)
                DispatchQueue.main.async {
                    self.newsList = tempNewList
                    self.loading = false
                }
            } catch {
                print(error.localizedDescription)
            }
        }.resume()
    }
}
