//
//  Masks.swift
//  Cube5
//
//  Created by 小波 on 2017/7/12.
//  Copyright © 2017年 xiaobo. All rights reserved.
//

struct Masks: OptionSet {
    let rawValue: Int
    
    static let ship   = Masks(rawValue: 1 << 0)
    static let bullet = Masks(rawValue: 1 << 1)
}
