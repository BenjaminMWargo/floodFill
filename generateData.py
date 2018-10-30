import random
import time
import sys
f = open('smalldata.txt','w')
s= ''
print("This will take awhile.")
for i in range(0,100):
    for j in range (0,100):
        x = random.randint(0,2)
        s += (str(i)+","+ str(j) + " " + str(x) + "\n")
    #time.sleep(1)
    #sys.stdout.write("\r%d%%" % (i/50))
    #sys.stdout.flush()
    f.write(s)
    s = ''
f.close()


