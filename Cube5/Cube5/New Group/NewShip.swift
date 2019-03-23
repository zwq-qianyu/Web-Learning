//
//  NewShip.swift
//  Cube5
//
//  Created by 小波 on 2017/7/18.
//  Copyright © 2017年 xiaobo. All rights reserved.
//

import SceneKit
import ARKit


extension ARSCNView {
    func addShip()  {
        let ship = Ship()
        
        let x = -0.5[0.5]
        let y = -0.5[0.5]

        ship.position = SCNVector3(x, y, -1)
        
        self.scene.rootNode.addChildNode(ship)
        
    }
}

extension Double {
    subscript(_ end : Double) -> Float {
        // 随机0到1之间的小数
        let factor = Double(arc4random()) / Double(UInt32.max)
        
        // 1到100间的随机数， 算法： 用大数减去 区间内的一个数
        // 100 - (100 - 1) * factor
        return Float(end - factor * (end - self))
    }
    
}



