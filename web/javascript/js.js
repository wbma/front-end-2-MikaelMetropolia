const startBox2 = document.getElementById("start-box-2");
const startBox1 = document.getElementById("start-box-1");
const button1 = document.getElementById("sheet-1");
const button2 = document.getElementById("sheet-2");
const button = document.getElementById("piano");



button.onclick = function () {
    if (startBox2.className !== "piano"){
        startBox2.className = "piano";
        startBox1.innerHTML = "Piano keyboard";
        startBox2.innerHTML = `<p>For starters, it is useful to familiarize yourself
                               with the use of a piano keyboard. Suppose you are sitting at a piano and do not
                               know a single note. Here's what you see:</p>
                                           <img src="tutorials_img/keyboard_88full.jpg" class="tutorial_img">
                                           <p>The keyboard consists of black and
                               white keys, and black are arranged in groups of two and three, or, we can say,
                               pairs and triples. This grouping makes it easier to find any desired note. For
                               example, the nearest white key to the left of a pair of black keys is <i
                                                       style='mso-bidi-font-style:normal'>do</i>, or, using the letters of the Latin
                               alphabet, <i style='mso-bidi-font-style:normal'>C</i>. If you move from C in
                               the order of the Latin alphabet, the nearest white key to the left of the
                               triplet of black, is indicated by the letter F (or <i style='mso-bidi-font-style:
                               normal'>fa</i>). All notes can be indicated in double: letters of the Latin
                               alphabet or names <i style='mso-bidi-font-style:normal'>do, re, mi, fa,</i>
                               etc. The illustration below shows the correspondence of the names of notes and
                               their Latin notation:</p>
                                           <img src="tutorials_img/2-octave-piano-keys.jpg" class="tutorial_img">
                                           <p>On the standard keyboard 8 notes <i style='mso-bidi-font-style:
                               normal'>do</i> (C). The note C in the centre of the keyboard, or the fourth on
                               the left, is called &quot;C of the first octave / middle C&quot;. Although the
                               piano keyboard looks very large, in fact it consists of several repetitions of
                               the same group of notes. Five different black and seven different white keys
                               are those twelve tones based on which in music for centuries the melodies and
                               harmonies of all those symphonies and songs that you just heard were composed.</p>

                                           <div class="task_tutorials">
                                               <p><b>Task 1:</b> If you are
                               in front of instrument now, try playing all twelve notes, starting from <i
                                                       style='mso-bidi-font-style:normal'>do</i> (C) of the first octave, moving from
                               left to right or vice versa.</p>
                                           </div>

                                           <p>You'll notice that the sound of the white keys is no
                               different from the sound of the black ones: if you close your eyes and try to
                               distinguish sound by pressing a white key or a black one, you will hardly be
                               able to do it (of course, if you do not have an absolute hearing when you hear
                               exactly which one note play). The question arises: why do we need black and white
                               keys?</p>



                                           <p>There are two main reasons. First, if all the keys
                               were white, then the keyboard would be half wider. Black keys are so inserted
                               between the white ones that essentially do not take up space. Imagine only that
                               you need to try to put a piano that has 88 only white keys (which is a standard
                               number of keys) of the usual width, into your room! Even if your room is
                               spacious enough for this, try to imagine how you would play it without a chair
                               with wheels. Secondly, thanks to space-saving black keys, it is possible to
                               play one octave, or the distance from one <i style='mso-bidi-font-style:normal'>do</i>
                               (C) to another, or from one <i style='mso-bidi-font-style:normal'>re</i> (D) to
                               another. The octave interval is very important, because it can double any <span
                                                       class=GramE>particular sound</span>. Having learned to play melodies with
                               different fingers of the right hand, you can find it more interesting in some
                               places to play them in an octave.</p>

                                           <div class="task_tutorials">
                                               <p><b>Task 2:</b> All you
                               need to know now is the white key designation: <i style='mso-bidi-font-style:
                               normal'>do</i> (C),<i style='mso-bidi-font-style:normal'> re</i> (D), <i
                                                       style='mso-bidi-font-style:normal'>mi</i> (E), etc. Press any keys and try to
                               name them. You will be surprised how soon you will do it without the slightest
                               difficulty.</p>
                                               </div>

                                           <p>Now let's define the names for the black keys:</p>

                                           <p>Their name is formed from the whites closest to them.
                               The first black key on which the arrow points is between the notes <i
                                                       style='mso-bidi-font-style:normal'>do</i>(C) and <i style='mso-bidi-font-style:
                               normal'>re</i>(D) and can be called in two ways: <i style='mso-bidi-font-style:
                               normal'>do-diesis</i>(C#) and <i style='mso-bidi-font-style:normal'>re-<span
                                                       class=SpellE>bemolle</span></i>(Db).</p>

                                           <p>The <i>sharp</i>/#/<i>diesis</i> indicates the note's pitch is
                               increased by a half-ton, and the <i>flat</i>/b/<span class=SpellE><i>bemolle</i></span> is
                               correspondingly a half-tone sound reduction. Thus, the difference between the
                               notes <i>do</i>(C) and <i>re</i>(D) is a tone. More about this we will talk in the next lessons.</p>

                                           <p>The second arrow indicates the <i>re-<span class=GramE>diesis<span style='font-style:normal'>(</span></span></i>D
                               #)/<i>mi-<span class=SpellE>bemolle</span></i>(<span class=SpellE>Eb</span>) key, etc.</p>
                                           <img src="tutorials_img/octave-piano-keys.jpg" class="tutorial_img">

                                           <div class="task_tutorials">
                                               <p><b>Task 3:</b> Give the
                               name to the black keys yourself and check the correctness of your judgments
                               according to the picture.</p>`;
    }
};


button1.onclick = function () {
    if (startBox2.className !== "sheet-1"){
        startBox2.className = "sheet-1";
        startBox1.innerHTML = "Music sheet 1";
        startBox2.innerHTML = " Something here. <br> Div id = start-box-2 <br>Viewing sheet 1 NOW";
    }
};


button2.onclick = function () {
    if (startBox2.className !== "sheet-2"){
        startBox2.className = "sheet-2";
        startBox1.innerHTML = "Music sheet 2";
        startBox2.innerHTML = " Something here. <br> Div id = start-box-2 <br> Viewing sheet 2 NOW";
    }
};