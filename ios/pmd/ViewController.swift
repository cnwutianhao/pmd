//
//  ViewController.swift
//  pmd
//
//  Created by 吴天昊 on 2022/4/17.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet var tableView: UITableView!

    private let cellReuseIdentifier = "NewsCell"
    private var newsList: [News]?

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(UINib(nibName: cellReuseIdentifier, bundle: nil), forCellReuseIdentifier: cellReuseIdentifier)
        NetworkManager().fetchNewsList { newsList in
            self.newsList = newsList
            DispatchQueue.main.async {
                self.tableView.reloadData()
            }
        }
    }

    func tableView(_: UITableView, numberOfRowsInSection _: Int) -> Int {
        return newsList?.count ?? 1
    }

    func tableView(_: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellReuseIdentifier, for: indexPath) as? NewsCell else {
            fatalError("Issue dequeuing")
        }
        cell.configure(with: newsList?[indexPath.row] ?? News(title: "Wait for it...", description: "", image: "https://img.ithome.com/newsuploadfiles/thumbnail/2022/4/614128_240.jpg?r=1650464489120"))
        return cell
    }

    func tableView(_: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }

    func tableView(_: UITableView, heightForRowAt _: IndexPath) -> CGFloat {
        return 106
    }
}
