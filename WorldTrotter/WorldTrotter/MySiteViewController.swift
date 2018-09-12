//
//  MySiteViewController.swift
//  WorldTrotter
//
//  Created by 梓逸宸 on 2018/9/12.
//  Copyright © 2018 梓逸宸. All rights reserved.
//

import UIKit
import WebKit

class MySiteViewController: UIViewController, WKUIDelegate {
    var webview: WKWebView!
    
    override func loadView() {
        // 创建一个网站视图
        let webConfiguration = WKWebViewConfiguration()
        let myframe = CGRect(x: 0, y: 0, width: 40, height: 100)
        webview = WKWebView(frame: myframe, configuration: webConfiguration)
        webview.uiDelegate = self
        //设置当前视图为该网站视图
        
        view = webview
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let myURL = URL(string: "https://www.runtofuture.cn")
        let myRequest = URLRequest(url: myURL!)
        webview.load(myRequest)
    }
}
