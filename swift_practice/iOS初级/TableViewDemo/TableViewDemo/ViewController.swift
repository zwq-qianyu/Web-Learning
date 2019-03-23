//
//  ViewController.swift
//  TableViewDemo
//
//  Created by 梓逸宸 on 2018/8/19.
//  Copyright © 2018年 梓逸宸. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDataSource {
    var weapons = ["AUG","汤姆逊冲锋枪","AKM","SCAR-L","M416","M16A4","Kar98Kar98K","WIN94","AWM","SKS","UMP9","MicroUZI","Gorza","DP28","平底锅","十字弩"]
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return weapons.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "WeaponCell", for: indexPath)
        cell.textLabel?.text = weapons[indexPath.row]
        return cell
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

