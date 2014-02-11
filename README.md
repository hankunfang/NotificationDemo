
[Android消息发送－主动拉取模式 ](http://hankunfang.blog.163.com/blog/static/18842839620141115410568/)


### Demo功能：

App主动拉取信息，并将该结果推送给用户。

### 涉及点：

service, thread, notification, alarmManager

### 设计思路：

1. 启动service后台服务；  
2. service另起线程从服务端获取数据；  
3. 根据数据结果，发送消息给用户；  
4. 定期功能通过AlarmManager实现。
