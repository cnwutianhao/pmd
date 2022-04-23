//
// Created by 吴天昊 on 2022/4/9.
//

import Foundation

struct News: Identifiable, Decodable {
    var id: Int { newsid }
    let newsid: Int
    let title: String
    let description: String
    let image: String
}
