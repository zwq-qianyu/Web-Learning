//
//  Bullet.swift
//  Cube5
//
//  Created by 小波 on 2017/7/12.
//  Copyright © 2017年 xiaobo. All rights reserved.
//

import UIKit
import SceneKit

class Bullet: SCNNode {
    override init() {
        super.init()
        
        let sphere = SCNSphere(radius: 0.025)
        self.geometry = sphere
        
        let shape = SCNPhysicsShape(geometry: sphere)
        self.physicsBody = SCNPhysicsBody(type: .dynamic, shape: shape)
        
        self.physicsBody?.isAffectedByGravity = false
        self.physicsBody?.categoryBitMask    = Masks.bullet.rawValue
        self.physicsBody?.contactTestBitMask = Masks.ship.rawValue
        
        let material = SCNMaterial()
        material.diffuse.contents = #imageLiteral(resourceName: "swift")
        
        self.geometry?.materials = [material]
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
