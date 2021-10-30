# product-management-using-springwebflux


prerequisite:
1. MongoDB should be installed in the machine.

----------------------------------------------------------------------
Below are the Endpoints:

#Get all order
#Method:
GET
#URL:
http://localhost:9090/allOrder

-----------------------------------------------------------------------

#Get order by orderId (replace {id} with orderId/UUID)
#Method:
GET
#URL:
http://localhost:9090/order/{id}

-----------------------------------------------------------------------

#Create a new order
#Method:
POST
#URL:
http://localhost:9090/createOrder
#BODY:
{   
"customerName" : "Knoldus",
"customerMobileNo" : "77777777",
"address" : "India",
"product" : [{"productId":9,
"item":"TouchPad",
"price":500
},
{"productId":8,
"item":"Mouse",
"price":100
}]
}

-----------------------------------------------------------------------
#update an existing order
#Method:
PUT
#URL:
http://localhost:9090/update
#BODY:
{"orderId": "e2d485f9-7b1d-4ef0-ac58-82a40650c2a9",
"orderDate": "2021-10-31T01:14:16.767645387",
"totalOrderPrice": 220,
"orderRequest": {
"customerName": "Knoldus",
"customerMobileNo": "777777777",
"address": "India",
"product": [
{
"productId": 7,
"item": "Keyboard",
"price": 120
},
{
"productId": 8,
"item": "Mouse",
"price": 100
}
]
}
}

-----------------------------------------------------------------------

#delete an order (replace {id} in URL with orderid)
#Method:
DELETE
#URL:
http://localhost:9090/deleteOrder/{id}

