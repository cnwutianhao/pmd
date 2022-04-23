//
//  NewsRow.swift
//  pmd
//
//  Created by 吴天昊 on 2022/4/9.
//

import SDWebImageSwiftUI
import SwiftUI

struct NewsRow: View {
    var news: News
    var body: some View {
        HStack {
            WebImage(url: URL(string: news.image))
                .resizable()
                .frame(width: 120, height: 90, alignment: /*@START_MENU_TOKEN@*/ .center/*@END_MENU_TOKEN@*/)

            VStack {
                Spacer()
                HStack {
                    Text(news.title)
                        .foregroundColor(.black)
                        .lineLimit(1)
                    Spacer()
                }
                HStack {
                    Text(news.description)
                        .foregroundColor(.gray)
                        .lineLimit(2)
                    Spacer()
                }
                Spacer()
            }
        }
    }
}
