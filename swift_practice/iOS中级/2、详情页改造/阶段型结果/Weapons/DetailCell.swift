//
//  DetailCell.swift
//  Weapons
//
//  Created by 梓逸宸 on 2018/8/20.
//  Copyright © 2018年 梓逸宸. All rights reserved.
//

import UIKit

class DetailCell: UITableViewCell {
    @IBOutlet weak var keyLabel: UILabel!
    @IBOutlet weak var valueLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
