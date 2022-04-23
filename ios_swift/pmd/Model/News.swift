//
//  News.swift
//  pmd
//
//  Created by 吴天昊 on 2022/4/17.
//

import Foundation

struct News: Codable {
    let title: String
    let description: String
    let image: String

    enum CodingKeys: String, CodingKey {
        case title
        case description
        case image
    }

    init(title: String, description: String, image: String) {
        self.title = title
        self.description = description
        self.image = image
    }
}
