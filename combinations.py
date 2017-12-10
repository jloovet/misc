#create array with array with all comb
#Find term case
#Find basic case to repeat


def combine(arr): 
    if len(arr) == 0:
        #return empty
        return [[]]
   
    #arr[1:] returns new array (mao 1 disapper every call, arr - head)
    restCombinations = combine(arr[1:]) #all after 1

    with_first = []
    #loop and add all combs to first
    for comb in restCombinations:
        with_first.append(arr[:1] + comb)
        
    return with_first + restCombinations    
    
r = [1,2,3,4,5,6,7,8]
print(len(combine(r)))
