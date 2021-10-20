# product-management-using-springwebflux


prerequisite:
1. MongoDB should be installed in the machine.

----------------------------------------------------------------------
Below are the Endpoints:

#Add Product
#Method: 
POST
#URL: 
http://localhost:9090/addProduct
#Body:
{
"productId":7,
"item":"user_1",
"price":101
}

-----------------------------------------------------------------------

#Get all the product available
#Method: 
GET
#URL:
http://localhost:9090/availableProduct

-----------------------------------------------------------------------

#Get all order
#Method:
GET
#URL:
http://localhost:9090/allOrder

-----------------------------------------------------------------------

#Create an order
#Method:
POST
#URL:
http://localhost:9090/createOrder
#BODY:
{
"product" : [{"productId":7,
"item":"user_1",
"price":101
}]
}

-----------------------------------------------------------------------
#update a order
#Method:
PUT
#URL:
http://localhost:9090/update
#BODY:
{
"orderId": "f2b104b3-172f-4ecb-a967-b953bc9e4ff1",
"orderRequest": {
"product": [
{
"productId": 7,
"item": "user_1",
"price": 101
}
]
}
}

-----------------------------------------------------------------------

#delete a order (replace the UUID with orderid)
#Method:
DELETE
#URL:
http://localhost:9090/deleteOrder/a79f3da4-30ae-4317-a05e-e469a9eb86d2

