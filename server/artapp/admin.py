from django.contrib import admin
from . import models
# Register your models here.
# 服务器远端用户名/密码：admin
admin.site.register(models.User)
admin.site.register(models.Data_record)
