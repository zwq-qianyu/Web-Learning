//
//  CardCell.swift
//  Weapons
//
//  Created by 梓逸宸 on 2018/8/19.
//  Copyright © 2018年 梓逸宸. All rights reserved.
//

import UIKit

class CardCell: UITableViewCell {
    @IBOutlet weak var typeLabel: UILabel!
    @IBOutlet weak var weaponLabel: UILabel!
    @IBOutlet weak var favBtn: UIButton!
    @IBOutlet weak var originLabel: UILabel!
    
    @IBOutlet weak var backImageView: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
