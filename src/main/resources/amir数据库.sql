##订单1--多产品型号
##产品型号1--多工序
##产品型号1--多耗材
##多工序 多-- 多 车间

#字典表
CREATE TABLE t_Dictionary(
	id int primary key auto_increment,
	type int comment '数据类型：1 材料类别  2材料单位',
	value varchar(50)
	);
	
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('1', '泡棉类');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('1', '主要面料类');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('1', '标类');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('1', '辅料类');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('1', '包装材料类');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '双');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '套');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '箱');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '只');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '件');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '米');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', 'SF');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '并');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '张');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', 'KG');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '个');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '卷');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '跟');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '片');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', 'm2');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '颗');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '付');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '顶');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '条');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('2', '块');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('3', '小手套');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('3', '大手套');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('3', '格斗手套');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('3', '靶类');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('3', '护具类');
INSERT INTO `product`.`t_Dictionary` (`type`, `value`) VALUES ('3', '其他类');

	
##部门
CREATE TABLE t_SysDept(
	id int primary key auto_increment,
	deptName varchar(50),
	deptOrder int
	);

INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('经理室', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('人事部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('劳资部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('出货部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('后勤部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('验货部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('外加工部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('质检部-成品检验', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('外协质检', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('行政部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('技术部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('生产部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('采购部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('下料车间', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('一车间', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('二车间', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('信息中心', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('后道车间-车工', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('后道车间-中辅工', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('后道车间-大辅工', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('包装车间', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('财务部', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('仓库', NULL);
INSERT INTO `product`.`t_SysDept` (`deptName`, `deptOrder`) VALUES ('样品间', NULL);

update t_SysDept set deptOrder = id;


##后台登录账号
CREATE TABLE t_SysUser(
	id int primary key auto_increment,
	deptId int,
	roleId int,
	loginName varchar(50) NULL,
	loginPwd varchar(100) NULL,
	userName varchar(50) NULL
	);
INSERT INTO `product`.`t_SysUser` (`deptId`, `roleId`, `loginName`, `loginPwd`, `userName`) VALUES ('1', '1', 'admin', '123456', '系统管理员');


##角色
CREATE TABLE t_SysRole(
  id int(11) primary key AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  description varchar(255) DEFAULT NULL,
  roleOrder int
);
INSERT INTO `product`.`t_SysRole` (`name`, `description`, `roleOrder`) VALUES ('管理员权限', NULL, '1');

##用户角色表
CREATE TABLE t_SysUserRole(
	id int(11) primary key AUTO_INCREMENT,
	userId int NOT NULL,
	roleId int NOT NULL
);

##菜单表
CREATE TABLE t_SysMenu(
	id int(11) primary key AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	url varchar(255),
	parentId int,
	createTime datetime,
	isDelete int NOT NULL
);
##角色菜单权限表
CREATE TABLE t_SysRoleMenu(
	id int(11) primary key AUTO_INCREMENT,
	roleId int NOT NULL,
	menuId int NOT NULL
);

##材料
CREATE TABLE t_PM(
	id int(11) primary key AUTO_INCREMENT,
	pmNo varchar(100) NOT NULL comment '物料编号',
	name varchar(100) NOT NULL comment '物料名称',
	type varchar(100) NOT NULL comment '类别 泡棉类、主要面料类、标类、辅料类、包装材料类',
	num decimal(8,4)  comment '数量',
	unit varchar(30) NOT NULL comment '单位 双、套、箱、只、件、米、SF、并、张、KG、个、卷、跟、片、m2、颗、付、顶、条、块',
	remark varchar(200) comment '备注',
	operator varchar(100) NOT NULL comment '添加人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int DEFAULT '0',
	colour varchar(100)  DEFAULT '' COMMENT '颜色',
  norms varchar(100)  DEFAULT '' COMMENT '规格',
  material varchar(100)  DEFAULT '' COMMENT '材质',
  callStr varchar(100)  DEFAULT '' COMMENT '称呼'
);
INSERT INTO `product`.`t_PM` (`pmNo`, `name`, `type`, `num`, `unit`, `remark`, `operator`, `createTime`, `lastModifyTime`, `isDelete`) VALUES ('TEST1', '测试1', '辅料类', '20', '双', 'SSS', '1', '2019-07-30 21:41:53', NULL, '0');
INSERT INTO `product`.`t_PM` (`pmNo`, `name`, `type`, `num`, `unit`, `remark`, `operator`, `createTime`, `lastModifyTime`, `isDelete`) VALUES ('DDD0', '002', '包装材料类', '0', '个', NULL, '1', '2019-07-30 21:57:22', NULL, '0');


##产品   原型中看不出产品信息
CREATE TABLE t_Product(
	id int(11) primary key AUTO_INCREMENT,
	productNo varchar(100) NOT NULL comment '产品型号',
	name varchar(100) NOT NULL comment '产品名称',
	type varchar(100) NOT NULL comment '类别 小手套 大手套 格斗手套 靶类 护具类 其他类',
	unit varchar(30) NOT NULL comment '单位 双、套、箱、只、件、米、SF、并、张、KG、个、卷、跟、片、m2、颗、付、顶、条、块',
	remark varchar(200) comment '备注',
	operator varchar(100) NOT NULL comment '添加人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	

##产品型号 耗材
CREATE TABLE t_ProductPM(
	id int(11) primary key AUTO_INCREMENT,
	productId int(11),
	productNo varchar(100) NOT NULL comment '产品型号',
	pmId int(11),
	pmNo varchar(100) NOT NULL comment '物料编号',
	pmName varchar(100) NOT NULL comment '物料名称',
	type varchar(100) NOT NULL comment '类别 a面料 b内胆 c辅料 d商标 e包装',
	unit varchar(30) NOT NULL comment '单位 双、套、箱、只、件、米、SF、并、张、KG、个、卷、跟、片、m2、颗、付、顶、条、块',
	remark varchar(200) comment '备注',
	status int(11) comment '0未确认  1确认',
	num decimal(5,2) comment '数量(耗料/双)',
	unitNum decimal(5,4) comment '数量(双/单位)',
	operator varchar(100) NOT NULL comment '添加人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int DEFAULT '0',
	sequence int(11) NOT NULL COMMENT '序号'
);
##材料入库单
CREATE TABLE t_PMIn(
	id int(11) primary key AUTO_INCREMENT,
	pmId int(11),
	pmNo varchar(100) NOT NULL comment '物料编号',
	pmName varchar(100) NOT NULL comment '物料名称',
	type varchar(100) NOT NULL comment '类别 a面料 b内胆 c辅料 d商标 e包装',
	unit varchar(30) NOT NULL comment '单位 双、套、箱、只、件、米、SF、并、张、KG、个、卷、跟、片、m2、颗、付、顶、条、块',
	remark varchar(200) comment '备注',
	supplier varchar(200) comment '供应商',
  inDate  date NULL COMMENT '入库时间',
	num decimal(8,4) comment '入库数量',
	perNum decimal(8,4) comment '入库前数量',
	backNum decimal(8,4) comment '入库后数量',
	operator varchar(100) NOT NULL comment '添加人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);

INSERT INTO `product`.`t_PMIn` (`pmId`, `pmNo`, `pmName`, `type`, `unit`, `remark`, `supplier`, `inDate`, `num`, `perNum`, `backNum`, `operator`, `createTime`, `lastModifyTime`, `isDelete`) VALUES ('1', 'TEST1', '测试1', '辅料类', '10', 'AAA', '！！！！', '2019-07-30', '10', '0', '10', '1', '2019-07-30 21:43:13', NULL, '0');
INSERT INTO `product`.`t_PMIn` (`pmId`, `pmNo`, `pmName`, `type`, `unit`, `remark`, `supplier`, `inDate`, `num`, `perNum`, `backNum`, `operator`, `createTime`, `lastModifyTime`, `isDelete`) VALUES ('1', 'TEST1', '测试1', '辅料类', '10', 'AAA', '！！！！', '2019-07-31', '10', '10', '20', '1', '2019-07-30 21:43:13', NULL, '0');


##领料单  耗料上限出库  好像可以直接用订单做主表
CREATE TABLE t_PMout_Bill(
	id int(11) primary key AUTO_INCREMENT,
	orderId int(11) comment '订单ID',
	orderNo varchar(100) NOT NULL comment '订单编号',
	productId int(11),
	productNo varchar(100) NOT NULL comment '产品型号',
	billNo varchar(30) NOT NULL comment '编号',
	type int NOT NULL comment '1 开领料单 2 订单号开单 3 计划外出库',
	workshop varchar(100) comment '车间',
	distributionNum int comment '分配数量',
	groupNmae varchar(30) comment '领用组',
	leader varchar(30) comment '负责人',
	pmType varchar(50) comment '材料类别',
	pmCheckItem varchar(50) comment '材料检测',
  stamp varchar(50) comment '电子章',
	operator varchar(100) NOT NULL comment '添加人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	
##领料单 详情（具体材料申领情况）
CREATE TABLE t_PMout_Bill_Detail(
	id int(11) primary key AUTO_INCREMENT,
	billId int NOT NULL comment '领料单ID',
	pmId int(11),
	pmNo varchar(100) NOT NULL comment '物料编号',
	pmName varchar(100) NOT NULL comment '物料名称',
	pmBatchNo varchar(100) NOT NULL comment '物料批次编号',
	num decimal(8,4) comment '数量',
	perNum decimal(8,4) comment '出库前数量',
	backNum decimal(8,4) comment '出库后数量',
	unit varchar(30) NOT NULL comment '单位 双、套、箱、只、件、米、SF、并、张、KG、个、卷、跟、片、m2、颗、付、顶、条、块',
	remark varchar(200) comment '备注',
	outInfo varchar(200) comment '出库说明',
	receiver varchar(100) comment '领用人',
	sender varchar(100)comment '发料人',
	operator varchar(100) NOT NULL comment '添加人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	
##客户
CREATE TABLE t_Customer(
	id int(11) primary key AUTO_INCREMENT,
	name varchar(100) NOT NULL comment '客户名称',
	industry varchar(100) comment '行业',
	salesman varchar(100) comment '业务员',
	source varchar(100) comment '客户来源',
	level varchar(100) comment '客户级别',
	product varchar(100) comment '主营产品',
	linkMan varchar(50) comment '联系人',
	phone varchar(30) comment '手机号',
	tel varchar(30) comment '联系电话',
	fax varchar(30) comment '传真',
	email varchar(100) comment '邮箱',
	address varchar(100) comment '地址',
	qq varchar(100),
	msn varchar(100),
	bankName varchar(100) comment '开户银行',
	bankNo varchar(100) comment '银行账号',
	legalPerson varchar(100) comment '法人代表',
	duty varchar(100) comment '税号',
	companyAddress varchar(100) comment '公司地址',
	remark varchar(100) comment '备注',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	

##订单
CREATE TABLE t_Order(
	id int(11) primary key AUTO_INCREMENT,
	orderNo varchar(100) NOT NULL comment '订单编号',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	
##订单 型号表
CREATE TABLE t_Order_Product(
	id int(11) primary key AUTO_INCREMENT,
	orderId int(11) comment '订单ID',
	orderNo varchar(100) NOT NULL comment '订单编号',
	productId int(11),
	productNo varchar(100) NOT NULL comment '产品型号',
	productType varchar(30) NOT NULL comment '类别 小手套 大手套 格斗手套 靶类 护具类 其他类',
	num int comment '数量',
	assignedNum int comment '已分配数额',
	notAssignNum int comment '未分配数额 （已分配数量+未分配数量 = 总数量）',
	maxNum int comment '上限数量',
	unit varchar(30) NOT NULL comment '单位 双、套、箱、只、件、米、SF、并、张、KG、个、卷、跟、片、m2、颗、付、顶、条、块',
	completeDate date comment '出货日期',
	customerId int comment '客户Id',
	customerName varchar(30) comment '客户',
	isMore int comment '是否追加 1追加 2不追加',
	urgentLevel int comment '紧急程度 1 正常 2 紧急',
	leader varchar(30) comment '负责人',
	pmType varchar(50) comment '材料类别',
	pmCheckItem varchar(50) comment '材料检测',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);

##生产单表
CREATE TABLE t_Production_Order(
   id int(11) primary key AUTO_INCREMENT,
   productionNo varchar(100) NOT NULL COMMENT '生产单编号',
   orderId int(11) NOT NULL comment '订单ID',
   orderNo varchar(100) NOT NULL comment '订单编号',
   productNo varchar(100) NOT NULL comment '产品型号',
   orderProductId  int(11) NOT NULL comment '订单 型号表ID',
   maxNum int comment '上限数量',
   assignNum int comment '分配数额',
   workshop varchar(30) comment '车间',
   workshopDirector varchar(100) comment '车间主任',
   isLuo int NOT NULL COMMENT '是否按罗分 0否 1 是',
   luoNum int(11) COMMENT '一罗的数量',
   printer varchar(100) COMMENT '打印人',
   printTime DATE COMMENT '打印时间',
   printCount int COMMENT '打印次数',
   codeUrl varchar(100) comment '二维码地址',
   createTime datetime,
	 lastModifyTime datetime,
	 isDelete int NOT NULL
);


##生产单-工序表
CREATE TABLE t_Production_Procedure(
   id int(11) primary key AUTO_INCREMENT,
   productionNo varchar(100) NOT NULL COMMENT '生产单编号',
   orderId int(11) NOT NULL comment '订单ID',
   orderNo varchar(100) NOT NULL comment '订单编号',
   productNo varchar(100) NOT NULL comment '产品型号',
   procedureId int(11) comment '工序ID',
   procedureName varchar(50) comment '工序名称',
   createTime datetime,
   sort int(11) NOT NULL comment '工序序号',
	 isDelete int NOT NULL
);

##生产单-罗表
CREATE TABLE t_Production_Luo(
  id int(11) primary key AUTO_INCREMENT,
  productionNo varchar(100) NOT NULL COMMENT '生产单编号',
  orderId int(11) NOT NULL comment '订单ID',
  orderNo varchar(100) NOT NULL comment '订单编号',
  productNo varchar(100) NOT NULL comment '产品型号',
  num int(11) NOT NULL comment '数量',
  maxNum int comment '上限数量',
  codeUrl varchar(100) DEFAULT NULL comment '二维码地址',
  createTime datetime,
	isDelete int NOT NULL
);


-- 暂时不用
##订单型号 车间
CREATE TABLE t_Order_Product_Workshop(
	id int(11) primary key AUTO_INCREMENT,
	orderProductId int(11) comment '订单型号表ID',
	workshop varchar(30) comment '车间',
	leader varchar(30) comment '负责人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	

## 工序单价管理列表
##产品 工序 车间
CREATE TABLE t_Product_Procedure_Workshop(
	id int(11) primary key AUTO_INCREMENT,
	productId int(11),
	productNo varchar(100) NOT NULL comment '产品型号',
	sort int(11) comment '排序',
	workshop varchar(30) comment '车间',
	procedureId int(11) comment '工序ID',
	procedureName varchar(50) comment '工序名称',
	price decimal(5,2) comment '价格',
	remark varchar(200) comment '备注',
	operator varchar(100) NOT NULL comment '添加人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	

##产品 工序 价格
CREATE TABLE t_Product_Procedure(
	id int(11) primary key AUTO_INCREMENT,
	procedureName varchar(50) comment '工序名称',
	productId int(11),
	productNo varchar(100) NOT NULL comment '产品型号',
	sort int(11) comment '排序',
	price decimal(5,2) comment '价格',
	operator varchar(100) NOT NULL comment '添加人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	


##员工
CREATE TABLE t_Emp(
	id int(11) primary key AUTO_INCREMENT,
	name varchar(100) NOT NULL comment '姓名',
	deptId int comment '部门ID',
	isLeader int comment '是否部门负责人 1是 2 不是',
	sex int comment '性别 1男 2女',
	workId int comment '工种ID',
	type int comment '类别 1计件工 2固定工',
	nativeSource varchar(100) comment '籍贯',
	Nation varchar(50) comment '名族',
	birthday varchar(30) comment '出生日期',
	idCard varchar(30) comment '身份证号',
	entryDate varchar(30) comment '入职时间',
	school varchar(100) comment '毕业学校',
	subject varchar(100) comment '专业',
	education varchar(100) comment '学历',
	linkMan varchar(50) comment '联系人',
	phone varchar(30) comment '手机号',
	tel varchar(30) comment '联系电话',
	address varchar(100) comment '地址',
	salary decimal(8,2) comment '工资',
	ylbx decimal(8,2) comment '养老保险',
	sybx decimal(8,2) comment '失业保险',
	yiliaobx decimal(8,2) comment '医疗保险',
	gjj decimal(8,2) comment '住房公积金',
	dhbt decimal(8,2) comment '工资',
	phoneSubsidy decimal(8,2) comment '电话费补贴',
	foodSubsidy decimal(8,2) comment '夜餐费',
	socialSubsidy decimal(8,2) comment '社保补贴',
	remark varchar(200) comment '备注',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	

##员工 工种
CREATE TABLE t_Emp_work(
	id int(11) primary key AUTO_INCREMENT,
	name varchar(100) NOT NULL comment '工种名称',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);

##工资月份数据
CREATE TABLE t_Salary_Monthly(
	id int(11) primary key AUTO_INCREMENT,
	yearMonth varchar(30) comment '年月',
	expectWorkDay int comment '规定上班天数',
	realityWorkDay int comment '正常上班天数',
	basicSalary decimal(8,2) comment '基本工资',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	

## 员工 月度考勤表
CREATE TABLE t_Emp_Salary_Monthly(
	id int(11) primary key AUTO_INCREMENT,
	yearMonth varchar(30) comment '年月',
	empId int comment '员工ID',
	empName varchar(30) comment '名字',
	deptId int comment '部门ID',
	deptName varchar(30) comment '部门名字',
	workName varchar(30) comment '工种',
	salary decimal(8,2) comment '工资',
	workDay int comment '上班天数',
	dayWork int comment '白班',
	nightWork int comment '晚班',
	score decimal(8,2) comment '分数',
	restDay int comment '公休',
	sumDay int comment '总天数',
	daySalary decimal(8,2) comment '日工资',
	realSalary decimal(8,2) comment '应发工资',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	


## 员工 月度各种补贴、扣款表
CREATE TABLE t_Emp_Subsidy_Monthly(
	id int(11) primary key AUTO_INCREMENT,
	yearMonth varchar(30) comment '年月',
	empId int comment '员工ID',
	empName varchar(30) comment '名字',
	deptId int comment '部门ID',
	deptName varchar(30) comment '部门名字',
	workName varchar(30) comment '工种',
	type int comment '类型 1电话费补贴 2固定工住房补贴 3计件工住房补贴 4工资补贴 5社保补贴 6工龄补贴 7车工出勤补贴 8新车工出勤补贴 9其他扣款',
	money decimal(8,2) comment '补贴（扣除）金额',
	billNo varchar(30) comment '票据编号',
	reason varchar(300) comment '补贴事项、扣款原因',
	drawer varchar(300) comment '开票人',
	drawTime datetime comment '开票时间',
	remark varchar(200) comment '备注',
	isConfirm int comment '是否确认 1确认 2未确认',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	

## 员工 计时工资表
CREATE TABLE t_Emp_TimeSalary_Monthly(
	id int(11) primary key AUTO_INCREMENT,
	yearMonth varchar(30) comment '年月',
	empId int comment '员工ID',
	empName varchar(30) comment '名字',
	deptId int comment '部门ID',
	deptName varchar(30) comment '部门名字',
	workName varchar(30) comment '工种',
	num decimal(8,3) comment '计算数量',
	price decimal(8,3) comment '单价',
	money decimal(8,2) comment '金额',
	billNo varchar(30) comment '票据编号',
	content varchar(300) comment '工作内容',
	drawer varchar(300) comment '开票人',
	drawTime datetime comment '开票时间',
	remark varchar(200) comment '备注',
	isConfirm int comment '是否确认 1确认 2未确认',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	


## 文件
CREATE TABLE t_File(
	id int(11) primary key AUTO_INCREMENT,
	url varchar(200) comment '存储目录',
	fileName varchar(50) comment '文件名',
	type int comment '文件类型 1员工信息 2 考勤信息 。。。。',
	operator varchar(30) comment '操作人',
	createTime datetime,
	lastModifyTime datetime,
	isDelete int NOT NULL
);	


