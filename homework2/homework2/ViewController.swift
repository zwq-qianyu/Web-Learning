//
//  ViewController.swift
//  homework2
//
//  Created by 梓逸宸 on 2018/8/18.
//  Copyright © 2018年 梓逸宸. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBAction func hitSwitch(_ sender: Any) {
        print("😜，你关闭了通往异世界的开关")
    }
    
    @IBAction func openSwitch(_ sender: Any) {
        print("恭喜，你打开了通往异世界的开关")
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

