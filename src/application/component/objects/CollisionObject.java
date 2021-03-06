package application.component.objects;

import java.util.List;

import application.component.objects.character.MovableObject;

public abstract class CollisionObject {
    /**
     * オブジェクトごとの衝突チェック
     * @param gameObject
     */
    public static void checkCollisions(List<GameObject> go, GameObject gameObject) {
        // gameObjectのスピード
        int x_speed, y_speed;
        
        // GameObjectからCollisionObjectを取り出す
        CollisionObject collisionGameObject = gameObject.getCollisionObject();
        // チェックしたいGameObjectをList<GameObject> goからピックアップ
        for ( int k = 0; k < go.size(); k++ ) {
            GameObject pickupGameObject = go.get(k);
            // 同じGameObjectまたはMovableObject以外ならcontinue
            if (pickupGameObject == gameObject || !(gameObject instanceof MovableObject)) {
                continue;
            }
            // GameObjectからCollisionObjectを引っ張ってくる
            CollisionObject pickupCollisionObject = pickupGameObject.getCollisionObject();
            
            // 当たり判定の先読み処理            
            // gameObjectをMovableObjectにキャスト
            MovableObject movableObject = (MovableObject)gameObject;
            // スピードを取ってくる
            x_speed = movableObject.getXSpeed();
            y_speed = movableObject.getYSpeed();
            
            // スピード分だけ移動させる
            collisionGameObject.move(x_speed, y_speed);
            // 衝突していたらigniteEventsを呼び出す
            if (pickupCollisionObject.isCollide(collisionGameObject)) {
//                TODO System.out.println("衝突");
                pickupCollisionObject.igniteEvents(pickupCollisionObject, gameObject, collisionGameObject);
                collisionGameObject.igniteEvents(collisionGameObject, gameObject, pickupCollisionObject);
            }

            // スピード分だけ戻す
            collisionGameObject.move( -x_speed, -y_speed);
        }
    }
    
    // イベントの発火
    public abstract void igniteEvents(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj);
    
    // 衝突判定
    public abstract boolean isCollide(CollisionObject collisionObject);    
    
    // 座標移動
    public abstract void transfer(int x, int y);

    public abstract void move(int dx, int dy);
}
