# Code file created by Pascal2016 compiler 2016-12-04 21:52:50
        .globl _main                         
        .globl main                         
_main:
main:
        call    prog$mini_1             
        movl    $0, %eax                
        ret                             
prog$mini_1:
        enter   $32, $1                 
        movl    $120,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        leave                           
        ret                             
