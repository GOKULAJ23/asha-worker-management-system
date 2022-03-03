from flask import *
from database import *

admin=Blueprint('admin',__name__)

@admin.route('/adminhome')
def adminhome():
	return render_template("adminhome.html")

@admin.route('/adminmanageashaworker',methods=['get','post'])
def adminmanageashaworker():
	data={}
	q="select * from ashaworker"
	res=select(q)
	data['ashaworker']=res
	if 'manage' in request.form:
		firstname=request.form['firstname']
		lastname=request.form['lastname']
		place=request.form['place']
		phone=request.form['phone']
		email=request.form['email']
		username=request.form['username']
		password=request.form['password']
		q="insert into login values(null,'%s','%s','ashaworker')" %(username,password)
		id=insert(q)
		q="insert into ashaworker values(null,'%s','%s','%s','%s','%s','%s')"%(id,firstname,lastname,place,phone,email)
		insert(q)
		return redirect(url_for('admin.adminmanageashaworker'))

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="delete":
		q="delete from login where login_id='%s'" %(id)
		delete(q)
		q="delete from ashaworker where login_id='%s'"%(id)
		delete(q)
		return redirect(url_for('admin.adminmanageashaworker'))
	if action=="update":
		q="select * from ashaworker where login_id='%s'" %(id)
		res=select(q)
		data['worker']=res
	if 'update' in request.form:
		firstname=request.form['firstname']
		lastname=request.form['lastname']
		place=request.form['place']
		phone=request.form['phone']
		email=request.form['email']
		q="update ashaworker set firstname='%s',lastname='%s',place='%s',phone='%s',email='%s' where login_id='%s'" %(firstname,lastname,place,phone,email,id)
		update(q)
		return redirect(url_for('admin.adminmanageashaworker'))

	return render_template("adminmanageashaworker.html",data=data)



@admin.route('/adminaddeventdetails',methods=['get','post'])
def adminaddeventdetails():
	data={}
	q="SELECT * FROM EVENT INNER JOIN ashaworker USING(worker_id)"
	res=select(q)
	data['event']=res

	q="select * from ashaworker"
	res=select(q)
	data['asha']=res

	if 'add' in request.form:
		event=request.form['event']
		asha=request.form['asha']
		date=request.form['date']
		q="insert into event values(null,'%s','%s','%s')" %(asha,event,date)
		insert(q)
		return redirect(url_for('admin.adminaddeventdetails'))
	return render_template('adminaddeventdetails.html',data=data)


@admin.route('/adminviewuser')
def adminviewuser():
	data={}
	q="select * from user"
	res=select(q)
	data['user']=res
	return render_template("adminviewuser.html",data=data)


@admin.route('/adminviewattendancemarked')
def adminviewattendancemarked():
	data={}
	id=request.args['id']
	q="select * from attendance where worker_id='%s'"%(id)
	res=select(q)
	data['attendance']=res
	return render_template("adminviewattendancemarked.html",data=data)

@admin.route('/adminviewhomeinspection')
def adminviewhomeinspection():
	data={}
	id=request.args['id']
	q="select * from inspection where worker_id='%s'" %(id)
	res=select(q)
	data['inspection']=res

	if 'action' in request.args:
		action=request.args['action']
		wid=request.args['wid']
	else:
		action=None
	if action=="accept":
		q="update inspection set status='accept' where inspection_id='%s'" %(wid)
		update(q)
		return redirect(url_for('admin.adminviewhomeinspection',id=id))
	if action=="reject":
		q="update inspection set status='reject' where inspection_id='%s'" %(wid)
		update(q)
		return redirect(url_for('admin.adminviewhomeinspection',id=id))

	return render_template("adminviewhomeinspection.html",data=data)



@admin.route('/adminviewimmunization')
def adminviewimmunization():
	data={}
	id=request.args['id']
	q="select * from immunization where worker_id='%s'" %(id)
	res=select(q)
	data['immunization']=res

	if 'action' in request.args:
		action=request.args['action']
		wid=request.args['wid']
	else:
		action=None
	if action=="accept":
		q="update immunization set status='accept' where immunization_id='%s'" %(wid)
		update(q)
		return redirect(url_for('admin.adminviewimmunization',id=id))
	if action=="reject":
		q="update immunization set status='reject' where immunization_id='%s'" %(wid)
		update(q)
		return redirect(url_for('admin.adminviewimmunization',id=id))
		
	return render_template("adminviewimmunization.html",data=data)

@admin.route('/adminviewhealthreport')
def adminviewhealthreport():
	data={}
	id=request.args['id']
	q="select * from health where worker_id='%s'" %(id)
	res=select(q)
	data['health']=res

	if 'action' in request.args:
		action=request.args['action']
		wid=request.args['wid']
	else:
		action=None
	if action=="accept":
		q="update health set status='accept' where health_id='%s'" %(wid)
		update(q)
		return redirect(url_for('admin.adminviewhealthreport',id=id))
	if action=="reject":
		q="update health set status='reject' where health_id='%s'" %(wid)
		update(q)
		return redirect(url_for('admin.adminviewhealthreport',id=id))

	return render_template("adminviewhealthreport.html",data=data)