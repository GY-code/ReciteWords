from django.urls import path

from . import views

urlpatterns = [
    path('login/', views.login, name='login'),
    path('auth/', views.auth, name='auth'),
    path('register/', views.register, name='register'),
    path('getUser/', views.getUser, name='getUser'),
    path('Record/', views.record, name='Record'),
    path('InforFiles/', views.inforFiles, name='InforFiles'),
    path('GetAllFiles/', views.getAllFiles, name='GetAllFiles'),


]