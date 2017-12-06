def send(message):
  "a function"
  def data_sender():
      "a nested function"
      print(message)
  return data_sender

fun2 = send("Here's a message!")
fun2()

sentence = "the car was dringing straight in to the sun"
words = sentence.split()
#create new array with length for all except one certian word
word_lengths = [len(word) for word in words if word != "the"]
print(words)
print(word_lengths)

from functools import partial
def multiply(x,y):
        return x * y

# create a new function that multiplies by 2
dbl = partial(multiply,2)
print(dbl(4))



# try decorators

def p_decorate(func):
   def func_wrapper(*args, **kwargs):
       return "<p>{0}</p>".format(func(*args, **kwargs))
   return func_wrapper

class Person(object):
    def __init__(self):
        self.name = "John"
        self.family = "Doe"

    @p_decorate
    def get_fullname(self):
        return self.name+" "+self.family

my_person = Person()

print my_person.get_fullname()


# try numpy arrays

weight_kg = [81.65, 97.52, 95.25, 92.98, 86.18, 88.45]
import numpy as np
# Create a numpy array np_weight_kg from weight_kg
np_weight_kg = np.array(weight_kg)
# Create np_weight_lbs from np_weight_kg
np_weight_lbs = np_weight_kg * 2.2
# Print out np_weight_lbs
print(np_weight_lbs)


# pandas...

# dict = {"car": ["V70", "M5", "911", "Golf"],
#       "color": ["Black", "Green", "Blue", "Black"],
#       "hp": [190, 400, 350, 95],
#       "speed": [200, 250, 290, 190] }
#import pandas as pd
#brics = pd.DataFrame(dict)
#print(brics)
