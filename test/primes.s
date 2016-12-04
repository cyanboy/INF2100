# Code file created by Pascal2016 compiler 2016-12-04 21:22:31
        .globl _main                         
        .globl main                         
_main:
main:
        call    prog$primes_1           
        movl    $0, %eax                
        ret                             
prog$primes_1:
        enter   $40, $1                 
proc$findprimes_2:
        enter   $40, $2                 
        movl    $2,%eax                 
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
.L0003:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setle   %al                     
        cmpl    $0, %eax                
        je      .L0004                  
        movl    $2,%eax                 
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
.L0005:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setle   %al                     
        cmpl    $0, %eax                
        je      .L0006                  
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
        jmp     .L0005                  
.L0006:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $1,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
        jmp     .L0003                  
.L0004:
        leave                           
        ret                             
proc$p4_7:
        enter   $32, $2                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $1000,%eax              
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setl    %al                     
        cmpl    $0, %eax                
        je      .L0008                  
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
.L0008:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $100,%eax               
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setl    %al                     
        cmpl    $0, %eax                
        je      .L0009                  
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
.L0009:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $10,%eax                
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setl    %al                     
        cmpl    $0, %eax                
        je      .L0010                  
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
.L0010:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        leave                           
        ret                             
proc$printprimes_11:
        enter   $40, $2                 
        movl    $2,%eax                 
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
        movl    $0,%eax                 
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
.L0012:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setle   %al                     
        cmpl    $0, %eax                
        je      .L0013                  
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        cmpl    $0, %eax                
        je      .L0014                  
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $0,%eax                 
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setg    %al                     
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $10,%eax                
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        pushl   %eax                    
        movl    $0,%eax                 
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        sete    %al                     
        movl    %eax, %ecx              
        popl    %eax                    
        andl    %ecx, %eax              
        cmpl    $0, %eax                
        je      .L0015                  
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
.L0015:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    proc$p4                 
        addl    $4,%esp                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $1,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
.L0014:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $1,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
        jmp     .L0012                  
.L0013:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        leave                           
        ret                             
        movl    $2,%eax                 
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
.L0016:
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setle   %al                     
        cmpl    $0, %eax                
        je      .L0017                  
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $1,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        movl    0(%ebp),%edx            
        movl    %eax,0(%edx)            
        jmp     .L0016                  
.L0017:
        call    proc$findprimes         
        addl    $0,%esp                 
        call    proc$printprimes        
        addl    $0,%esp                 
        leave                           
        ret                             
