//
//  NewsCell.swift
//  pmd
//
//  Created by 吴天昊 on 2022/4/17.
//

import UIKit

class NewsCell: UITableViewCell {
    @IBOutlet private var titleLabel: UILabel!
    @IBOutlet private var subtitleLabel: UILabel!
    @IBOutlet private var leftImageView: UIImageView!

    func configure(with news: News) {
        titleLabel.text = news.title
        titleLabel.textColor = UIColor.black
        titleLabel.font = UIFont.boldSystemFont(ofSize: 20)
        titleLabel.numberOfLines = 1

        subtitleLabel.text = news.description
        subtitleLabel.textColor = UIColor.gray
        subtitleLabel.numberOfLines = 2

        leftImageView.downloadedFrom(imageUrl: news.image)
    }
}

extension UIImageView {
    func downloadedFrom(imageUrl: String) {
        let url = URL(string: imageUrl)!
        let request = URLRequest(url: url)
        let session = URLSession.shared
        let dataTask = session.dataTask(with: request, completionHandler: {
            data, _, error -> Void in
            if error != nil {
                print(error.debugDescription)
            } else {
                let img = UIImage(data: data!)
                DispatchQueue.main.async {
                    self.image = img
                }
            }
        }) as URLSessionTask

        dataTask.resume()
    }
}
