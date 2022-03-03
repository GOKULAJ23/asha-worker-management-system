from flask import *
from database import *
import demjson
import uuid


api=Blueprint('api',__name__)

@api.route('/login')
def login():
	data={}
	username=request.args['username']
	password=request.args['password']
	q="select * from login where username='%s' and password='%s'" %(username,password)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	return demjson.encode(data)

@api.route('/userregister')
def userregister():
	data={}
	fname=request.args['fname']
	lname=request.args['lname']
	place=request.args['place']
	phone=request.args['phone']
	email=request.args['email']
	username=request.args['username']
	password=request.args['password']
	q="select * from login where username='%s' and password='%s'" %(username,password)
	res=select(q)
	if res:
		data['status']="duplicate"
	else:
		q="insert into login values(null,'%s','%s','user')" %(username,password)
		id=insert(q)
		q="insert into user values(null,'%s','%s','%s','%s','%s','%s')" %(id,fname,lname,place,phone,email)
		insert(q)
		data['status']="success"
	return demjson.encode(data)


@api.route('/userviewworker')
def userviewworker():
	data={}
	q="select * from ashaworker"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewworker"
	return demjson.encode(data)


@api.route('/usersendenquiry')
def usersendenquiry():
	data={}
	lid=request.args['lid']
	wid=request.args['wid']
	enquiry=request.args['enquiry']
	q="insert into enquiry values(null,(select user_id from user where login_id='%s'),'%s','%s','pending',curdate())" %(lid,wid,enquiry)
	insert(q)
	data['status']="success"
	data['method']="usersendenquiry"
	return demjson.encode(data)


@api.route('/userviewenquiry')
def userviewenquiry():
	data={}
	lid=request.args['lid']
	q="select * from enquiry inner join ashaworker using(worker_id) where user_id=(select user_id from user where login_id='%s')" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewenquiry"
	return demjson.encode(data)

@api.route('/uservieweventnotification')
def uservieweventnotification():
	data={}
	wid=request.args['wid']
	q="select * from event where worker_id='%s'"%(wid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	return demjson.encode(data)


@api.route('/ashaworkerviewenquiry')
def ashaworkerviewenquiry():
	data={}
	lid=request.args['lid']
	q="select * from enquiry inner join user using(user_id) where worker_id=(select worker_id from ashaworker where login_id='%s')" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	return demjson.encode(data)


@api.route('/ashaworkersendreply')
def ashaworkersendreply():
	data={}
	reply=request.args['reply']
	eid=request.args['eid']
	q="update enquiry set reply='%s' where enquiry_id='%s'" %(reply,eid)
	update(q)
	data['status']="success"
	return demjson.encode(data)


@api.route('/ashaworkermarkattendance')
def ashaworkermarkattendance():
	data={}
	lid=request.args['lid']
	q="insert into attendance values(null,(select worker_id from ashaworker where login_id='%s'),curdate())"%(lid)
	insert(q)
	data['status']="success"
	data['method']="ashaworkermarkattendance"
	return demjson.encode(data)


@api.route('/ashaworkerviewattendance')
def ashaworkerviewattendance():
	data={}
	lid=request.args['lid']
	q="select * from attendance inner join ashaworker using(worker_id) where worker_id=(select worker_id from ashaworker where login_id='%s')" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="ashaworkerviewattendance"
	return demjson.encode(data)


@api.route('/ashaworkeraddimmunization')
def ashaworkeraddimmunization():
	data={}
	lid=request.args['lid']
	q="insert into immunization values(null,(select worker_id from ashaworker where login_id='%s'),curdate(),'pending')"%(lid)
	insert(q)
	data['status']="success"
	data['method']="ashaworkeraddimmunization"
	return demjson.encode(data)


@api.route('/ashaworkerviewimmunization')
def ashaworkerviewimmunization():
	data={}
	lid=request.args['lid']
	q="select * from immunization inner join ashaworker using(worker_id) where worker_id=(select worker_id from ashaworker where login_id='%s')" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="ashaworkerviewimmunization"
	return demjson.encode(data)

@api.route('/ashaworker_addhealthreport',methods=['get','post'])
def ashaworker_addhealthreport():
	data={}
	logid=request.form['logid']
	health=request.form['health']
	image=request.files['image']
	path="static/"+str(uuid.uuid4())+image.filename
	image.save(path)
	q="insert into health values(null,(select worker_id from ashaworker where login_id='%s'),'%s','%s',curdate(),'pending')"%(logid,health,path)
	insert(q)
	data['status']="success"
	data['method']="ashaworker_addhealthreport"
	return demjson.encode(data)


@api.route('/ashaworkerviewhealthreport')
def ashaworkerviewhealthreport():
	data={}
	lid=request.args['lid']
	q="select * from health inner join ashaworker using(worker_id) where worker_id=(select worker_id from ashaworker where login_id='%s')" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="ashaworkerviewhealthreport"
	return demjson.encode(data)


@api.route('/ashaworker_addinspectionreport',methods=['get','post'])
def ashaworker_addinspectionreport():
	data={}
	logid=request.form['logid']
	details=request.form['details']
	image=request.files['image']
	path="static/"+str(uuid.uuid4())+image.filename
	image.save(path)
	q="insert into inspection values(null,(select worker_id from ashaworker where login_id='%s'),'%s','%s','pending')"%(logid,details,path)
	insert(q)
	data['status']="success"
	data['method']="ashaworker_addinspectionreport"
	return demjson.encode(data)


@api.route('/ashaworkerviewinspection')
def ashaworkerviewinspection():
	data={}
	lid=request.args['lid']
	q="select * from inspection inner join ashaworker using(worker_id) where worker_id=(select worker_id from ashaworker where login_id='%s')" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="ashaworkerviewinspection"
	return demjson.encode(data)


@api.route('/ashaworkerviewneventotification')
def ashaworkerviewneventotification():
	data={}
	lid=request.args['lid']
	q="select * from event where worker_id=(select worker_id from ashaworker where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	return demjson.encode(data)


@api.route('/userviewashaworkerdetail')
def userviewashaworkerdetail():
	data={}
	q="select * from ashaworker"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	return demjson.encode(data)