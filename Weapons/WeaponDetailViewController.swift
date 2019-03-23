//
//  WeaponDetailViewController.swift
//  Weapons
//
//  Created by 梓逸宸 on 2018/8/20.
//  Copyright © 2018年 梓逸宸. All rights reserved.
//

import UIKit

class WeaponDetailViewController: UIViewController {
    var imageName = ""
    
    @IBOutlet weak var headerImageView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        print(imageName)
        
        headerImageView.image = UIImage(named: imageName)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
