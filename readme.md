# 使用描述

---
1. 创建数据库，名为hsm
2. 修改context.xml
3. 执行createTable.sal
4. 执行createRow.sql

# 页面描述  

---  
1.	登陆注册页面  
2.	经理管理页面：显示所有客房名以及与客房类型，显示房间入住数量与剩余数量与客房类型入住数量与剩余数量的统计
3.	经理管理客房类型：显示具体客房类型信息并提供修改接口（描述价格）
4.	经理修改客房信息：（描述），同时显示该房间所有预定（未入住和已入住未结账）并可修改描述
5.	经理管理账号：新增或删除前台账号
6.	前台，或经理或客户或无用户查询可用房间（仅可用类型与日期筛选）
7.	客户浏览房间信息（该信息包含类型）并选择预定
8.	前台，或经理查询出预定信息并完成入住，延长入住时间（同上分两栏）入住完成结账
9.	无用户要求：浏览简介主页

# 接口描述

---
所有接口如果有正常调用一定有返回值，没有返回值代表未登录或缺少参数
>
>1.第一次冲刺  
>>  1. 用户登录接口
	url：/UserLogin，需求参数UserCode,UserPass,UserType
	返回登录用户的全部信息，并会放进session中  
>>  2. 用户注册接口
	url: /UserRegister, 需求参数 UserCode,UserPass
	返回注册用户的全部信息，并会放进session中  
>>  3. 新增前台账号接口
	url: /NewReception, 需求参数 UserCode,UserPass,执行要求：登录账号manager
	返回登录用户的全部信息  
>>  4. 删除前台账号接口
url: /DeleteReception, 需求参数 UserCode 执行要求：登录账号manager
	返回删除成功或删除失败  
>>  5. 返回所有前台账户 url: /GetReception, 需求参数：无 执行要求：登录账号manager
>>  6. 返回当前登录用户接口
url:/GetUser,无需求参数，无返回代表无登陆
>
>2.第二次冲刺  
>>1.返回所有客房类型及其基本信息与该类的当日剩余房间
	url:/GetType    
2.返回当日客房入住或预定数量与剩余数量 返回接口RoomStatus{}
	url:/GetRoomStatus  
3.返回所有客房信息接口
	url:/GetRoomAll   
4.返回选定的类型
	url:/GetTypeByTid 需求参数Tid   
5.修改房间类型信息  
	url:/ModifyType 需求参数：Tid,TypeName,TypePrice 执行要求：登录账号manager
	返回修改成功或修改失败  
6.修改房间信息
	url:/ModifyRoom	需求参数：Rid,Floor,Feature,Orientation,Tid执行要求：登录账号manager  
7.返回指定房间的房间所有信息接口并包含所有预定
	url:/GetRoom 需求参数：Rid 执行要求：允许普通页面调用，供统计可租住日期  
8.返回当前可入住房间接口
	url:/GetRoomEmpty 需求参数 日期 类型（可无） 
>
>3.第三次冲刺
>>1.客户预定房间接口
	url:/NewBook 需求参数：Rid StartTIme EndTime 执行要求：登录账号client 或前台可再提供身份证号
	返回信息，预定成功或失败原因例如改时间段中有时间已被预定  
2.返回客户所有订单信息
	url:/GetBookByUserCode 需求参数：UserCode 执行要求：登录账号 已登陆即可  
3.前台查询预定并完成入住接口
	url:/CheckBook 需求参数Bid 执行要求：登录账号reception  
4.前台延长入住时间接口
url:/DelayBook 需求参数Bid, EndTime 执行要求：登录账号reception  
5.前台完成结账接口
	url:/PayBook 需求参数Bid 执行要求：登录账号reception
> 
>4.第四次冲刺
>>1.通过Uid返回用户的所有入住人信息
url:/GetCheckPeopleByUid 需求参数：Uid(非client登录时) 执行要求：登录账号 如果Uid=0代表搜索的是client创建的特殊入住人员  
2.通过Bid返回该订单的所有入住人信息
url:/GetCheckPeopleByBid需求参数：Bid 执行要求：前台登录     
3.新建CheckPeople
url:/NewCheckPeople 需求参数：CheckName，CheckCode,CheckPhone,执行要求，登录用户,用户创建的会自动归属在用户下
4.将CheckPeople链接到订单中
url:/AddCheckPeopleToBook 需求参数：Cid，Bid 执行要求：登录账户
