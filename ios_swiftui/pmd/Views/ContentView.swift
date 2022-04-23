//
//  ContentView.swift
//  pmd
//
//  Created by 吴天昊 on 2022/4/9.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject var networkMangaer = NetworkManager()

    var body: some View {
        NavigationView {
            VStack {
                if networkMangaer.loading {
                    Text("Loading...")
                } else {
                    List(networkMangaer.newsList.newslist) { news in
                        NewsRow(news: news)
                    }
                }
            }.navigationBarTitle(Text("News List"))
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
