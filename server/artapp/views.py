import os
import random
import time

# Create your views here.
from django.core import serializers
from django.core.files.base import ContentFile
from django.core.files.storage import default_storage
from django.http import HttpResponse
from .models import *
import json
from . import zhenzismsclient as smsclient
import re

# Create your views here.

# apply for sdk supplier
appId = '******'
appSecret = '************************************************'
apiUrl = 'https://sms_developer.zhenzikj.com'

code_dict = {}
time_dict = {}
# 秒
delta_time = 300


# 获取验证码
def login(request):
    json_str = request.body
    json_dic = json.loads(json_str)
    tel = json_dic.get('tel')
    print(tel)
    if not re.match(r'^1[3-9]\d{9}$', tel):
        return HttpResponse('invalid')
    # 生成验证码
    code = ''
    print(code)
    for num in range(1, 5):
        code = code + str(random.randint(0, 9))
    client = smsclient.ZhenziSmsClient(apiUrl, appId, appSecret)
    params = {}
    params['number'] = tel
    params['templateId'] = '6175'
    params['templateParams'] = [code, '5分钟']
    response = json.loads(client.send(params))
    # response = {}
    # response['code'] = 0
    if response['code'] != 0:
        print(response['data'])
        return HttpResponse('wrong_code')
    else:
        print("验证码" + code + "发送成功")
        code_dict[tel] = code
        time_dict[tel] = time.time()
        # return HttpResponse('success')
        return HttpResponse('success')


# 验证验证码
def auth(request):
    json_str = request.body
    json_dic = json.loads(json_str)
    code = json_dic.get('code')
    tel = json_dic.get('tel')
    if not (tel in code_dict):
        # return HttpResponse('not_exist')
        return HttpResponse('fail')
    if time.time() - time_dict[tel] > delta_time:
        print(time.time() - time_dict[tel])
        # return HttpResponse('overtime')
        return HttpResponse('fail')
    if code == code_dict[tel]:
        exist = 1
        try:
            User.objects.get(tel=tel)
        except User.DoesNotExist:
            exist = 0
        if exist:
            return HttpResponse('success')
        else:
            local_register(tel)
            print('register')
            return HttpResponse('success')
    else:
        return HttpResponse('fail')


def local_register(tel):
    user = User(tel=tel)
    user.save()


# 新用户获得注册信息
def register(request):
    json_dic = json.loads(request.body.decode("utf-8"))
    print(request.body.decode("utf-8"))
    print(json_dic)
    user = User(**json_dic)  # 将json序列转换成model
    user.save()
    print(user.toJSON())
    return HttpResponse('success')


# 老用户获得自己用户信息
def getUser(request):
    json_dic = json.loads(request.body.decode("utf-8"))
    tel = json_dic.get('tel')
    try:
        select_User = User.objects.get(tel=tel)
        return HttpResponse(select_User.toJSON())
    except (KeyError, User.DoesNotExist):
        return HttpResponse('not_exist')


# SERVER_UPLOAD_INFO_ADDRESS 1
def inforFiles(request):
    idNum = request.POST.get('sinaNum')
    target_path = './static/' + idNum + '/'
    uploaded_file = request.FILES.get('uploadedFile')
    target_file = target_path + uploaded_file.name
    print('SERVER_UPLOAD_INFO_ADDRESS: target_path:' + target_path)
    if not os.path.isdir(target_path):
        os.makedirs(target_path)
    if os.path.exists(target_file):
        os.remove(target_file)
    path = default_storage.save(target_file, ContentFile(uploaded_file.read()))
    return HttpResponse()


# SERVER_UPLOAD_RECORD_ADDRESS 2
def record(request):
    idNum = int(request.POST.get('sinaNum'))
    updateType = int(request.POST.get('updateType'))
    print('SERVER_UPLOAD_INFO:' + str(idNum) + ' ' + str(updateType))
    dr = Data_record(idNum=idNum, update_type=updateType)
    dr.save()
    res_dic = {
        'state': '1',
        'content': '上传成功'
    }
    json_dict = json.dumps(res_dic)
    return HttpResponse(json_dict)


# SERVER_RETURN_BOOKS_ADDRESS
def getAllFiles(request):
    idNum = request.POST.get('sinaNum')
    target_path = './static/' + idNum + '/'
    if os.path.isdir(target_path):
        return HttpResponse('find')
    else:
        return HttpResponse('no_exist')
