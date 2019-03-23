//
//  WeaponsTableViewController.swift
//  Weapons
//
//  Created by 梓逸宸 on 2018/8/19.
//  Copyright © 2018年 梓逸宸. All rights reserved.
//

import UIKit

class WeaponsTableViewController: UITableViewController {
    var weapons = ["AUG","AWM","十字弩","DP28","Groza","Kar98k","M16a4","Micro UZI","平底锅","SKS","UMP9"]
    
    var weaponType = ["自动步枪","狙击枪","冷兵器","机枪","自动步枪","狙击枪","自动步枪","冲锋枪","近身武器","半自动步枪","冲锋枪"]
    
    var origins = ["奥地利","英国","中国","前苏联","俄罗斯","德国","美国","以色列","美国","前苏联","美国"]

    var weaponsImages = [
        "aug","awm","crossbow",
        "dp28","groza","kar98k",
        "m16a4","microuzi","pan",
        "sks","ump9"
    ]
    
    var favorites = Array(repeating: false, count: 11)
    
    //trailing ... 左滑操作   UIContextualAction、UISwipeActionsConfiguration
    override func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        
        //删除行操作
        let delAction = UIContextualAction(style: .destructive, title: "delete") { (_, _, completion) in
            self.weapons.remove(at: indexPath.row)
            self.weaponType.remove(at: indexPath.row)
            self.weaponsImages.remove(at: indexPath.row)
            self.origins.remove(at: indexPath.row)
            self.favorites.remove(at: indexPath.row)
            
            tableView.deleteRows(at: [indexPath], with: .automatic)
            
            completion(true)
        }
        
        //分享一行操作
        let shareAction = UIContextualAction(style: .normal, title: "share") { (_, _, completion) in
            let text = "这是绝地求生中的\(self.weapons[indexPath.row])"
            let image = UIImage(named: self.weaponsImages[indexPath.row])!
            
            //调用系统自带的分享功能
            let ac = UIActivityViewController(activityItems: [text, image], applicationActivities: nil)
            //处理iPad的bug,使得在iPad上直接弹出一个小框即可
            if let pc = ac.popoverPresentationController{
                if let cell = tableView.cellForRow(at: indexPath){
                    pc.sourceView = cell
                    pc.sourceRect = cell.bounds
                }
            }
            self.present(ac, animated: true)
            
            completion(true)
        }
        
        shareAction.backgroundColor = .orange
        
        let config = UISwipeActionsConfiguration(actions: [delAction, shareAction])
        return config
    }
    
    //leading ... 右滑操作   UIContextualAction、UISwipeActionsConfiguration
    override func tableView(_ tableView: UITableView, leadingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        let favAction = UIContextualAction(style: .normal, title: "like") { (_, _, completion) in
            completion(true)
        }
        
        favAction.image = #imageLiteral(resourceName: "unfav")
        favAction.backgroundColor = UIColor.purple
        
        let config = UISwipeActionsConfiguration(actions: [favAction])
        return config
    }
    
    @IBAction func favBtnTap(_ sender: UIButton) {
        let btnPos = sender.convert(CGPoint.zero, to: self.tableView)  //将sender相对自己的位置（0，0）转换成相对于 tableView 的位置
//        print("爱心按钮相对于tableview的位置是：\(btnPos)")
        let indexPath = tableView.indexPathForRow(at: btnPos)!      //根据相对tableView 的位置，找到 indexpath
//        print("爱心按钮所在的indexpath为：\(indexPath)")
        self.favorites[indexPath.row] = !self.favorites[indexPath.row]
        
        let cell = tableView.cellForRow(at: indexPath) as! CardCell
        cell.favorite = self.favorites[indexPath.row]
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return weapons.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let id = String(describing: CardCell.self)
        let cell = tableView.dequeueReusableCell(withIdentifier: id, for: indexPath) as! CardCell
        
        cell.typeLabel.text = weaponType[indexPath.row]
        cell.weaponLabel.text = weapons[indexPath.row]
        cell.originLabel.text = origins[indexPath.row]
        cell.backImageView.image = UIImage(named: weaponsImages[indexPath.row])
        cell.favorite = favorites[indexPath.row]
        
        return cell
    }

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */


    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        let row = tableView.indexPathForSelectedRow!.row
        let destination = segue.destination as! WeaponDetailViewController
        
        destination.imageName = weaponsImages[row]
    }


}
