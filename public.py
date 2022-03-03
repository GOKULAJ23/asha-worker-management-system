from flask import *
from database import *

public=Blueprint('public',__name__)


@public.route('/')
def home():
	return render_template("home.html")

@public.route('/login',methods=['get','post'])
def login():
	if 'login' in request.form:
		username=request.form['username']
		password=request.form['password']
		q="select * from login where username='%s' and password='%s'" %(username,password)
		res=select(q)
		if res:
			usertype=res[0]['usertype']
			if usertype=="admin":
				return redirect(url_for('admin.adminhome'))
		else:
			flash("invalid username and password")
	return render_template("login.html")