# Generated by Django 4.0 on 2021-12-25 23:38

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('artapp', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Data_record',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('idNum', models.DecimalField(decimal_places=0, max_digits=12)),
                ('update_type', models.DecimalField(decimal_places=0, max_digits=1)),
                ('update_time', models.DateTimeField(auto_now_add=True)),
            ],
        ),
    ]
