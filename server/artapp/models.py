from decimal import Decimal

from django.core.serializers.json import DjangoJSONEncoder
from django.db import models


# Create your models here.
# desperate method
class convertJSON():
    def toJSON(self):
        fields = []
        for field in self._meta.fields:
            fields.append(field.name)

        d = {}
        import datetime
        for attr in fields:
            if isinstance(getattr(self, attr), datetime.datetime):
                d[attr] = getattr(self, attr).strftime('%Y-%m-%d %H:%M:%S')
            elif isinstance(getattr(self, attr), datetime.date):
                d[attr] = getattr(self, attr).strftime('%Y-%m-%d')
            else:
                d[attr] = getattr(self, attr)

        import json
        return json.dumps(d, cls=DjangoJSONEncoder)


class Data_record(models.Model):
    idNum = models.DecimalField(max_digits=12, decimal_places=0, editable=False)
    update_type = models.DecimalField(max_digits=2, decimal_places=0, editable=False)
    update_time = models.DateTimeField(auto_now_add=True, editable=False)

    def __str__(self):
        if self.update_type == 1:
            return str(self.idNum) + "\t上传操作\t|\t\t" + self.update_time.strftime("%Y-%m-%d %H:%M:%S")
        else:
            return str(self.idNum) + "\t恢复操作\t|\t\t" + self.update_time.strftime("%Y-%m-%d %H:%M:%S")

    class Meta:
        ordering = ['-update_time']
        verbose_name = '用户备份与恢复日志'
        verbose_name_plural = '用户备份与恢复日志'


class User(models.Model, convertJSON):
    tel = models.CharField(max_length=20, unique=True, primary_key=True)
    password = models.CharField(max_length=50,default='123456')
    nickname = models.CharField(max_length=50,default='My Profile')
    genderChoice = (
        ('none', '保密'),
        ('male', '男'),
        ('female', '女'),
    )
    birthday = models.DateField(null=True, blank=True)
    gender = models.CharField(max_length=10, choices=genderChoice, default='保密')
    description = models.CharField(max_length=500, default=None, null=True, blank=True)
    email = models.EmailField(unique=True, default=None, null=True, blank=True)

    c_time = models.DateTimeField(auto_now_add=True, blank=True)

    def __str__(self):
        return self.tel

    def display(self):
        return '123'

    class Meta:
        ordering = ['c_time']
        verbose_name = '用户'
        verbose_name_plural = '用户'


def user_directory_path(instance, filename):
    # file will be uploaded to STATIC_ROOT/user_<id>/<filename>
    return '{0}/{1}/{2}'.format(instance.type, instance.id, filename)

