from tkinter import *
from PIL import ImageTk, Image
from playsound import playsound
import pygame

# ======starting out====
window = Tk()
window.title('sweet dreams')
window.iconbitmap('icons8-crescent-moon-48.ico')
window.configure(background="#F4F1F8")
window.state('zoomed')
window.rowconfigure(0, weight=1)
window.columnconfigure(0, weight=1)
frame1 = Frame(window, bg='#F4F1F8')
frame2 = Frame(window, bg='#F4F1F8')
frame3 = Frame(window, bg='#F4F1F8')
# ===========LOOP===
for frame in [frame1, frame2, frame3]:
    frame.grid(row=0, column=0, sticky='nsew')


# ==========Functions====
def show_frame(frame):
    frame.tkraise()


# global paused
# paused = False
#
#
# def pause(is_paused):
#     global paused
#     paused = is_paused
#     if paused:
#         pygame.mixer.music.unpause()
#         paused = False
#     else:
#         pygame.mixer.music.pause()
#         paused = True


# ===========page1====#
Empty = Label(frame1, text=" \n \n \n \n \n \n \n\n \n\n\n\n\n\n\n \n\n \n\n\n\n\n\n\n \n\n \n\n\n\n\n\n",
              bg="#F4F1F8").grid(row=1, column=3)
frame1_story = Label(frame1,
                     text="\t \t  Breathe in and breathe out...\n \t \t  Let us thank God together for all his blessings.",
                     bg="#F4F1F8", font='Comic 30 bold')
frame1_story.grid(row=3, column=3)
frame1_next = Button(frame1, text='next', command=lambda: show_frame(frame2))
frame1_next.grid(row=7, column=10)
frame1_back = Button(frame1, text='back', command=lambda: show_frame(frame3))
frame1_back.grid(row=7, column=1)
PLAYING = Button(frame1, text="Sound",command=lambda: playsound("page_1.mp3"))
PLAYING.grid(row=3, column=10)
# pause = Button(frame1, text="pause")
# pause.grid(row=2, column=10)
my_image1 = Image.open("page_one.PNG")
my_image1 = my_image1.resize((989, 568), Image.ANTIALIAS)
my_image1 = ImageTk.PhotoImage(my_image1)
my_label1 = Label(frame1, image=my_image1)
my_label1.grid(row=1, column=3)
# ===========page2====
Empty = Label(frame2, text=" \n \n \n \n \n \n \n\n \n\n\n\n\n\n\n \n\n \n \n \n \n \n\n\n\n\n\n").grid(row=1, column=1)
frame2_title = Label(frame2,
                     text="\tThank you God for my hands, legs, nose, mouth and eyes.\n  \tThank you God for a brain "
                          "that keeps me learning. \n \t  (Give yourself a very big hug)\n \t  And promise yourself "
                          "that you will take care of you body.", bg="#F4F1F8", font='Comic 30 bold ')
frame2_title.grid(row=3, column=3)
frame2_btn = Button(frame2, text='next', command=lambda: show_frame(frame3))
frame2_btn.grid(row=7, column=10)
frame1_back = Button(frame2, text='back', command=lambda: show_frame(frame1))
frame1_back.grid(row=7, column=1)
PLAYING = Button(frame2, text="Sound",command=lambda: playsound("page_2.mp3"))
PLAYING.grid(row=3, column=10)
# pause = Button(frame2, text="pause")
# pause.grid(row=1, column=10)
my_image2 = Image.open("page_two.PNG")
my_image2 = my_image2.resize((958, 579), Image.ANTIALIAS)
my_image2 = ImageTk.PhotoImage(my_image2)
my_label2 = Label(frame2, image=my_image2)
my_label2.grid(row=1, column=3)
# ===========page3====
Empty = Label(frame3, text=" \n \n \n \n \n \n \n\n \n\n\n\n\n\n\n \n\n \n\n\n\n\n\n\n \n\n \n\n\n\n\n\n").grid(row=1,
                                                                                                                column=1)
frame3_title = Label(frame3, text="\tBy exercising , drinking water, \n \tAnd eating mighty superhero and heroine "
                                  "vegetables and fruits. ", bg="#F4F1F8", font='Comic 30 bold')
frame3_title.grid(row=3, column=3)
frame3_btn = Button(frame3, text='next', command=lambda: show_frame(frame1))
frame3_btn.grid(row=7, column=10)
frame1_back = Button(frame3, text='back', command=lambda: show_frame(frame2))
frame1_back.grid(row=7, column=1)
PLAYING = Button(frame3, text="play",command=lambda: playsound("page_3.mp3"))
PLAYING.grid(row=3, column=10)
# pause = Button(frame3, text="pause")
# pause.grid(row=2, column=10)
my_image3 = Image.open("paage_three.png")
my_image3 = my_image3.resize((1430, 588), Image.ANTIALIAS)
my_image3 = ImageTk.PhotoImage(my_image3)
my_label3 = Label(frame3, image=my_image3)
my_label3.grid(row=1, column=3)

# ===========page4====


window.mainloop()
